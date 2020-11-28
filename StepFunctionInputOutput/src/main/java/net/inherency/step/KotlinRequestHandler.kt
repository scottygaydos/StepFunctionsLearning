package net.inherency.step

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import net.inherency.step.Dependencies.Companion.objectMapper
import java.io.InputStream
import java.io.OutputStream

/**
 * A facade interface to make Kotlin implementation of RequestHandlers cleaner/easier, especially with regards to data
 * classes.  We do have the side effect of requiring an inputType field in any handler that implements this
 * interface, but that side effect is the price we pay for strongly typed object mapping.
 *
 * Mostly copied, but rearranged/formatted from here:
 * https://stackoverflow.com/questions/60519593/how-do-you-register-the-kotlinmodule-to-the-aws-lambda-jackson-object-mapper
 */
interface KotlinRequestHandler<I: Any, O: Any?> : RequestStreamHandler {
    val inputType: Class<I> //This must be provided in the constructor or as a getter of any class that implements this interface.

    fun <T : Any> readJson(clazz: Class<T>, stream: InputStream): T =
            objectMapper.readValue(stream, clazz)

    fun <T : Any> InputStream.readJson(clazz: Class<T>): T =
            readJson(clazz, this)

    fun Any?.writeJsonNullable(outputStream: OutputStream) {
        if (this != null)  objectMapper.writer().writeValue(outputStream, this)
    }

    //This is the function that must be implemented by those who implement our facade interface.
    fun handleRequest(input: I, context: Context): O?

    //This implements the function required by AWS's interface definition.
    override fun handleRequest(inputStream: InputStream, outputStream: OutputStream, context: Context) {
        handleRequest(inputStream.readJson(inputType), context).writeJsonNullable(outputStream)
    }
}