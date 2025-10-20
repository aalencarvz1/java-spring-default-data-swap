# DefaultDataSwap

This class is part of the Java project and is responsible for data swapping functionality.

## 📄 Overview

The `DefaultDataSwap` class provides mechanisms to exchange, serialize, or transform data objects in a flexible way.  
It may serve as a default implementation for data interchange layers or utility components.

## ⚙️ Key Features

- Default implementation for data swapping logic
- Supports flexible input/output data handling
- Integrates easily with other modules

## 🧩 Example Usage

```java
// Example usage
DefaultDataSwap swapper = new DefaultDataSwap();
swapper.data = data;
swapper.succes = true;
return swapper;
```

## 🛠️ Implementation

Below is the full implementation of the class:

```java
package com.oiis.libs.java.spring.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import java.util.Objects;


/**
 * Default class for represent generic data swap between processes.
 *
 * @author aalencarvz1
 * @version 1.0.0
 */
public class DefaultDataSwap {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDataSwap.class);

    /**
     * the success indicative
     */
    public boolean success = false;

    /**
     * the data to swap
     */
    public Object data = null;

    /**
     * the message, if necessary
     */
    public String message = null;

    /**
     * the http status code
     */
    public Integer httpStatusCode = null;

    /**
     * the exception if occurs
     */
    public Exception exception = null;

    public DefaultDataSwap() {}

    public DefaultDataSwap(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public void setException(Exception exception) {
        logger.error("parameter error on setException",exception);
        this.httpStatusCode = Objects.requireNonNullElse(this.httpStatusCode, HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.exception = exception;
        if (!StringUtils.hasText(this.message) && this.exception != null) {
            this.message = this.exception.getMessage();
        }
    }

    public ResponseEntity<DefaultDataSwap> sendHttpResponse() {
        return this.success ? ResponseEntity.status(HttpStatus.OK).body(this) : ResponseEntity.status(Objects.requireNonNullElse(this.httpStatusCode, HttpStatus.INTERNAL_SERVER_ERROR.value())).body(this);
    }
}

```

## 📚 License

This code is part of a private project. Please check the repository license for usage permissions.
