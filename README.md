# shi/Java
a Simple Hackable Interpreter in Java

## Notes
The project includes a prebuilt `.jar` file. Run without arguments to start the REPL, with to load and evaluate the specified files.

```
java -jar shi.jar ../shi/benchmarks/run.shi
```

## Implementation
Operations are compiled to closures before evaluation.