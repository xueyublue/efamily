echo on
rem Creating Tables...
cd C:\efamily\database\table
sqlplus xueyu/xueyu @CreateAll.sql

rem Importing Initial Data...
cd C:\efamily\database\initial
sqlplus xueyu/xueyu @ImportAll.sql

exit
