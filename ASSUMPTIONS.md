# Assumptions & Challenges Overcome

## Assumptions

### 1. Java 17 Compatibility
- The code is expected to run on **Java 17+** without requiring external dependencies.
- The assessment assumes usage of **java.util.concurrent** for thread management.

### 2. Intended Functionality
- The application is designed to **identify and fix issues** related to:
  - 🛑 Compilation errors
  - ⚠️ Runtime exceptions
  - 🔄 Concurrency issues
  - 🕵️‍♂️ Deadlocks & resource leaks
- The expected outcome is a **fully functional, error-free, and optimized** Java application.

### 3. Error Handling
- Proper exception handling is required instead of failing silently.
- Logging is used for debugging critical failures.
- Null checks and input validations are necessary to prevent crashes.

### 4. Concurrency & Synchronization
- The provided code contains potential **race conditions**, which should be fixed using `synchronized` blocks or concurrent collections.
- **Deadlocks** must be resolved by ensuring lock acquisition happens in a **consistent order**.

### 5. Code Optimizations
- Infinite loops should be avoided or provided with an exit condition.
- Resource leaks (e.g., open `Scanner` objects) should be handled using **try-with-resources**.
- Use `ExecutorService.shutdown()` properly to ensure graceful thread termination.

---

## CSVHelper

### ✅ Assumptions
- Empty rows (completely blank) should **not be processed**.
- Duplicate records **must be removed**, while maintaining the order of appearance.
- The file should be read and written using **UTF-8 encoding**.
- The application should **not crash** if the file is missing or unreadable.

### 🚀 Challenges & Fixes

✅ **Empty Line Handling:**
- **Issue:** Previously, blank lines between records were still being processed.
- **Fix:** Used `filter(line -> !line.trim().isEmpty())` to ignore completely empty rows.

✅ **Duplicate Handling:**
- **Issue:** Duplicate records were being retained.
- **Fix:** Used `LinkedHashSet` to **preserve order** while removing duplicates.

✅ **Error Handling:**
- **Issue:** File operations could crash the application.
- **Fix:** Wrapped file operations in **try-catch** blocks to prevent crashes.

---

## TroubleshootingTest

### ✅ Assumptions
- The test cases simulate **common Java runtime issues**, such as:
  - `NullPointerException`
  - `NumberFormatException`
  - **Deadlocks & Concurrency Issues**
  - **Unclosed Resources**

### 🚀 Challenges & Fixes

✅ **Fixing Deadlocks**
- **Issue:** The original code had a **potential deadlock** due to inconsistent lock ordering.
- **Fix:** Implemented a **strict lock acquisition order** to prevent circular waits.

✅ **Handling `NullPointerException`**
- **Issue:** The method `safeCauseNullPointer()` attempted to call `.length()` on `null`.
- **Fix:** Wrapped it in a `try-catch` block and returned `-1` instead of crashing.

✅ **Handling `NumberFormatException`**
- **Issue:** The method `safeParseInteger("ABC")` caused an exception.
- **Fix:** Wrapped parsing in a `try-catch` block and **logged the issue** instead of throwing an error.

✅ **Resource Management (`Scanner` Issue)**
- **Issue:** The `Scanner` was not being closed properly, causing a potential resource leak.
- **Fix:** Used **try-with-resources** to ensure proper closure.

---

## Challenges Overcome

### 1. Fixing Compilation Errors
- Resolved **missing method calls** and **incorrect variable assignments**.
- Corrected **syntax issues** and ensured method signatures matched expected usage.

### 2. Handling Runtime Exceptions
- Implemented **safe parsing** of integers to handle `NumberFormatException`.
- Introduced **null checks** to prevent `NullPointerException`.
- Wrapped potential error-prone operations in **try-catch blocks**.

### 3. Resolving Concurrency Issues
- Replaced `HashMap` with `ConcurrentHashMap` to handle concurrent modifications safely.
- Used `synchronized` blocks to prevent race conditions while updating shared data.

### 4. Eliminating Deadlocks
- Ensured **lock acquisition follows a strict order** to avoid circular wait conditions.
- Used `tryLock()` where applicable to prevent indefinite waiting.

### 5. Preventing Infinite Loops & Resource Leaks
- Removed infinite loops or introduced **break conditions**.
- Properly closed **Scanner objects** using **try-with-resources**.
- Ensured **ExecutorService shutdown** to prevent resource leaks.

---

## ⏳ Time Spent on Assessment
- **Total hours invested:** ~3 hours