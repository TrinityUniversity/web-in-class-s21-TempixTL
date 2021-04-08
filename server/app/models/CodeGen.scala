package models

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.run(
    "slick.jdbc.PostgresProfile",
    "org.postgresql.Driver",
    "jdbc:postgresql://localhost/tasklist?user=tom&password=password",
    "/home/tom/Code/class/webapps/web-in-class-s21-TempixTL/server/app/",
    "models", None, None, true, false
  )
}
