# Assumptions & Challenges Overcome

## Assumptions
1. **Java 17 Compatibility**  
   - The code is expected to run on **Java 17+** without requiring external dependencies.
   - The assessment assumes usage of **java.util.concurrent** for thread management.

2. **Intended Functionality**  
   - The application is designed to **identify and fix issues** related to:
     - Compilation errors 🛑
     - Runtime exceptions ⚠️
     - Concurrency issues 🔄
     - Deadlocks & resource leaks 🕵️‍♂️
   - The expected outcome is a fully functional, error-free, and optimized Java application.

3. **Error Handling**  
   - Proper exception handling is required instead of failing silently.
   - Logging is used for debugging critical failures.
   - Null checks and input validations are necessary to prevent crashes.

4. **Concurrency & Synchronization**  
   - The provided code contains potential **race conditions**, which should be fixed using synchronized blocks or concurrent collections.
   - **Deadlocks** must be resolved by ensuring lock acquisition happens in a consistent order.

5. **Code Optimizations**  
   - Infinite loops should be avoided or provided with an exit condition.
   - Resource leaks (e.g., open `Scanner` objects) should be handled using try-with-resources.
   - Use `ExecutorService.shutdown()` properly to ensure graceful thread termination.

## Challenges Overcome
1. **Fixing Compilation Errors**  
   - Resolved missing method calls and incorrect variable assignments.
   - Corrected syntax issues and ensured method signatures matched expected usage.

2. **Handling Runtime Exceptions**  
   - Implemented safe parsing of integers to handle `NumberFormatException`.
   - Introduced null checks to prevent `NullPointerException`.
   - Wrapped potential error-prone operations in try-catch blocks.

3. **Resolving Concurrency Issues**  
   - Replaced `HashMap` with `ConcurrentHashMap` to handle concurrent modifications safely.
   - Used `synchronized` blocks to prevent race conditions while updating shared data.

4. **Eliminating Deadlocks**  
   - Ensured lock acquisition follows a strict order to avoid circular wait conditions.
   - Used `tryLock()` where applicable to prevent indefinite waiting.

5. **Preventing Infinite Loops & Resource Leaks**  
   - Removed infinite loops or introduced break conditions.
   - Properly closed `Scanner` objects using try-with-resources.
   - Ensured `ExecutorService` shutdown to prevent resource leaks.

## Time Spent on Assessment
- **Total hours invested:** 2 hours

