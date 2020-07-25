package com.kishan.hive_jdbc

import java.sql.DriverManager

import org.json.{JSONArray, JSONObject}

object Connect {

  def main(args: Array[String]): Unit = {

    //Same as other JDBC connections -- Without Kerberos
    val connectionURL: String = "jdbc:hive2://192.168.181.130:10000/default" //Connection String with the default database
    val userName: String = "cloudera"
    val password: String = "cloudera"

    //Create Connection
    val conn = DriverManager.getConnection(connectionURL, userName, password)

    //Create Execution Object
    val stmt = conn.createStatement()

    //Query to Execute
    val query = "SELECT * FROM array_table"

    //Execute Query
    val rs = stmt.executeQuery(query)

    //Metadata from the query
    val resultMetadata = rs.getMetaData

    //Preparing list with the column names
    var columns: List[String] = List()
    println("----------------------Table Meta Information--------------------------")
    for (i <- 1 to resultMetadata.getColumnCount) {
      println(s"Column Index: ${i}, Column Name: ${resultMetadata.getColumnName(i)}, Column Type: ${resultMetadata.getColumnTypeName(i)}")
      columns = columns :+ resultMetadata.getColumnName(i)
    }

    // Creating JSON Out of the ResultSet
    println("\n----------------------------Result of Query-------------------------")
    var resultJSONArray: JSONArray = new JSONArray()
    while (rs.next()) {
      var json: JSONObject = new JSONObject()
      var key: String = ""
      for (column <- columns) {
        var columnValue = rs.getObject(column)
        if (column == "array_table.marks") {
          json.put(column, new JSONArray(columnValue.toString))
        } else {
          if (column == "struct_table.marks") {
            json.put(column, new JSONObject(columnValue.toString))
          } else {
            json.put(column, columnValue)
          }

        }
      }
      resultJSONArray.put(json)
    }
    println(resultJSONArray.toString(2)) //Printing the Result JSON Array
  }
}
