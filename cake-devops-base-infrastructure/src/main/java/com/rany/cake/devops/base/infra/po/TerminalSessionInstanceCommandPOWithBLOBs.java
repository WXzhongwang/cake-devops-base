package com.rany.cake.devops.base.infra.po;

public class TerminalSessionInstanceCommandPOWithBLOBs extends TerminalSessionInstanceCommandPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance_command.input
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    private String input;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance_command.input_formatted
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    private String inputFormatted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance_command.output
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    private String output;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance_command.input
     *
     * @return the value of terminal_session_instance_command.input
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    public String getInput() {
        return input;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance_command.input
     *
     * @param input the value for terminal_session_instance_command.input
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    public void setInput(String input) {
        this.input = input == null ? null : input.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance_command.input_formatted
     *
     * @return the value of terminal_session_instance_command.input_formatted
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    public String getInputFormatted() {
        return inputFormatted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance_command.input_formatted
     *
     * @param inputFormatted the value for terminal_session_instance_command.input_formatted
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    public void setInputFormatted(String inputFormatted) {
        this.inputFormatted = inputFormatted == null ? null : inputFormatted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance_command.output
     *
     * @return the value of terminal_session_instance_command.output
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    public String getOutput() {
        return output;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance_command.output
     *
     * @param output the value for terminal_session_instance_command.output
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    public void setOutput(String output) {
        this.output = output == null ? null : output.trim();
    }
}