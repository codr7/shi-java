# shi-java
a Simple Hackable Interpreter in Java

## Notes
The project includes a prebuilt `.jar` file. Run without arguments to start the REPL, with to load and evaluate the specified files.

```
java -jar shi.jar ../shi/benchmarks/run.shi
```

## Implementation
The implementation weighs in at roughly 1.3kloc.

### VM
VM operations are compiled to closures that return the next PC, the evaluation loop calls the next closure until the specified end PC is reached.