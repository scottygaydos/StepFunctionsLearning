package net.inherency.step

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler

/**
 * A very flexible Kotlin Lambda handler that tries to manage all inputs and outputs by itself.  The step function
 * could still keep/throw away things regardless of what the output map here does, however, so this is generally a
 * very poor way of implementing a handler if you are just using this kind of handler "to get step functions to work."
 */
@Suppress("unused")
class InputsAsMapHandler: RequestHandler<MutableMap<Any, Any>, MutableMap<Any, Any>> {

    override fun handleRequest(state: MutableMap<Any, Any>, context: Context): MutableMap<Any, Any> {
        context.logger.log(state.toString())
        state["newKey"] = "newValue" //just add a new key/value pair for demonstration purposes
        return state
    }
}