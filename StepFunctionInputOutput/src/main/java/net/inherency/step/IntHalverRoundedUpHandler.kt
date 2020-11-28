package net.inherency.step

import com.amazonaws.services.lambda.runtime.Context
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * A cleaner Kotlin Lambda Handler that better defines the input we care about and output we care about.  This is
 * important; the lambda suddenly doesn't have to have massive objects with lots of fields that we don't understand
 * or care about.  The glue of what to do with the output of this lambda is handled by the step function definition,
 * so the data state as a whole is managed in one place, which is that step function definition.
 *
 * Input: The step function must provide a JSON object with a field key "intValue" of number (integer) type.  All other
 * fields are ignored because of how we define objectMapper in the Dependencies class companion object.
 */
@Suppress("unused")
class IntHalverRoundedUpHandler(override val inputType: Class<Input>) : //We implement inputType in constructor.
        KotlinRequestHandler<IntHalverRoundedUpHandler.Input, Int> {

    override fun handleRequest(input: Input, context: Context): Int {
        return input.intValue.toBigDecimal()
                .divide(BigDecimal(2), RoundingMode.UP)
                .toInt()
    }

    data class Input (
            val intValue: Int
    )

}