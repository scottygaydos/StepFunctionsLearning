package net.inherency.step

import com.amazonaws.services.lambda.runtime.Context

/**
 * A cleaner Kotlin Lambda Handler that better defines the input we care about and output we care about.  This is
 * important; the lambda suddenly doesn't have to have massive objects with lots of fields that we don't understand
 * or care about.  The glue of what to do with the output of this lambda is handled by the step function definition,
 * so the data state as a whole is managed in one place, which is that step function definition.
 */
@Suppress("unused")
class StringToIntHandler: KotlinRequestHandler<StringToIntHandler.Input, Int> {

    override fun handleRequest(input: Input, context: Context): Int {
        val inputString = input.inputString
        return inputString.toInt()
    }

    data class Input (
            val inputString: String
    )

    //We implement inputType as a hard-coded field by overriding get() instead of using the constructor in this variation.
    override val inputType: Class<Input>
        get() = Input::class.java
}