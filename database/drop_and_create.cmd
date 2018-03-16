echo on
rem Creating Tables...
cd C:\efamily\database\table
sqlplus efamily/efamily @CreateAll.sql

rem Importing Initial Data...
cd C:\efamily\database\initial
sqlplus efamily/efamily @ImportAll.sql

exit
