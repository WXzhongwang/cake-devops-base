package com.rany.cake.devops.ssh.base.commond;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class TestCommand {
    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }
}