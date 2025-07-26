# shi-java
a [Simple Hackable Interpreter](https://github.com/codr7/shi) in Java

## Running
The project includes a prebuilt `.jar` file. Run without arguments to start the REPL, with to load and evaluate the specified files.

```
java -jar shi.jar ../shi/benchmarks/run.shi
```

## Implementation
The code weighs in at roughly 1.3kloc.

### Values
Values and types are divided into two parts, an untyped interface (`IType`/`IValue`) and a typed implementation (`ScriptType`/`Value`).

### VM
VM operations are compiled to closures that return the next PC, the evaluation loop calls the next closure until the specified end PC is reached.