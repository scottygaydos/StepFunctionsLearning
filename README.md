# AWS - Step Function I/O Learning Project

This project contains source code and supporting files for a serverless step function in AWS for purposes of learning 
about the Input and Output processing options for each step.  Source details:

- StepFunctionInputOutput/src/main - Code for the application's Lambda functions. 
- stepfunction.yaml - The step function definition with comments.  These comments must be removed before deploying to 
AWS; the file is not truly yaml / does not truly support comments.  I recommend reading these comments - they were the 
crux of things I had to learn for purposes of this project.


Additionally, replace lambda resource ARNs to use the correct ARN for your account.  

## Testing/Deploying (Requires Java8+ and Maven)

Use maven to create a jar for the lambdas:
```mvn clean package```

Use the created fat jar to create lambdas used by the step function.  To minimize errors and editing, name your lambdas
like this:

InputsAndOutputsAsMap

StringToIntFunction

IntHalverRoundedUpFunction