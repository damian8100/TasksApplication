call runcrud
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo GRADLEW BUILD has errors – breaking work
goto fail

:rename
start firefox http://localhost:8080/crud/v1/tasks
goto end

:fail
echo.
echo ERROR!!

:end
echo.
echo Work is finished