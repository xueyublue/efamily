@ECHO OFF

net stop OracleServiceORCL
net stop OracleVssWriterORCL
net stop OracleOraDB12Home1TNSListener

net start OracleOraDB12Home1TNSListener
net start OracleServiceORCL
net start OracleVssWriterORCL

PAUSE...