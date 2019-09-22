package com.kishan.apacheParser

import org.apache.commons.cli.{DefaultParser, Option, Options}

object apacheParserUserDefinedOptions {
  def main(args: Array[String]): Unit = {

    //Definition of Arguments
    val name: Option = Option.builder()
      .hasArg
      .longOpt("name")
      .argName("n")
      .desc("Name of the student")
      .build()

    val age: Option = Option.builder()
      .hasArg
      .longOpt("age")
      .argName("a")
      .desc("Age of the student")
      .build()

    val options = new Options
    options.addOption(name)
    options.addOption(age)

    //Parsing of arguments
    val parser = new DefaultParser
    val parsedArguments = parser.parse(options, args)

    //Interrogation of arguments
    if (parsedArguments.hasOption("name")){
      println(s"Value in the name argument is: ${parsedArguments.getOptionValue("name")}")
    } else {
      println("Name argument not used")
    }

    if (parsedArguments.hasOption("age")){
      println(s"Value in the age argument is: ${parsedArguments.getOptionValue("age")}")
    } else {
      println("Age argument not used")
    }

  }
}
