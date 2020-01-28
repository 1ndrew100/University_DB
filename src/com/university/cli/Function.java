package com.university.cli;

import java.io.IOException;

@FunctionalInterface
interface Function {
    void execute() throws IOException;
}