package com.kishan.apacheParser

import org.apache.commons.cli._

object apacheParserUsage {

  def main(args: Array[String]): Unit = {

    //Definition for the parser
    val options = new Options
    options.addRequiredOption("n", "name", true, "Name of the student")
    options.addOption("a", "age", true, "Age of the student")

    try {
      //Parsing the arguments
      val parser = new DefaultParser
      val parsedArguments = parser.parse(options, args)

      //Interrogation of arguments
      if (parsedArguments.hasOption("name")) {
        println(s"Value in the name argument is: ${parsedArguments.getOptionValue("name")}")
      } else {
        println("Name argument not used")
      }

      if (parsedArguments.hasOption("age")) {
        println(s"Value in the age argument is: ${parsedArguments.getOptionValue("age")}")
      } else {
        println("Age argument not used")
      }
    } catch {
      case m: MissingOptionException => {
        println(s"Required Missing Arguments: ${m.getMissingOptions}")
        help(getClass.getName, options)
      }
      case a: MissingArgumentException => {
        println(s"Missing Argument for the options: ${a.getOption}")
        help(getClass.getName, options)
      }
    }
  }

  def help(appname: String, appOptions: Options): Unit = {
    val helpFormatted = new HelpFormatter
    helpFormatted.printHelp(appname, appOptions)
  }
}
