@echo OFF
cd..
cd..
cd..
cd..
cd..
cd..
cd..
cd..
cd CSIS-Concerns
cd Concerns
cd CVS-GLV-Tool

FOR /F "tokens=1,2 delims==" %%G IN (cvs-glv.properties) DO (set %%G=%%H)
@echo ###################################################################SHEKA#######
@echo                   Module Preperation Tool v. 1.1.0
@echo                     Ashraf Gaber @ Beshara Group
@echo                              19 May 2014
@echo ###################################################################SHEKA#######
 
set "arg1=%~1"

IF "%arg1%"=="" (

	@echo:
	set /p moduleName=Enter Your Module Name:%=%
	
) ELSE (
	
	@echo                              19 May 2014
	set moduleName=%arg1%
)

call cvs-glv-config.bat moduleName
@echo:
@echo Now I will use 'cvs-glv.properties' to get a clean version of :
IF [%MODULE_PAGES_PATH%] neq [] (
	@echo - JSFBase.
	@echo - JSFIntegration.
	@echo - GRS.INTG-PRS.
	@echo - REG.INTG-PRS.
	@echo - INF.INTG-PRS.
	@echo - JOB.INTG-PRS.
	@echo - QUL.INTG-PRS.
	@echo - ORG.INTG-PRS.
	@echo - TRN.INTG-PRS.
	@echo - MER.INTG-PRS.
	@echo - EMP.INTG-PRS.
	@echo - BNK.INTG-PRS.
	@echo - BGT.INTG-PRS.
	@echo - DED.INTG-PRS.
	@echo - SCP.INTG-PRS.
)
@echo ###################################################################SHEKA#######
@echo  NOW WILL Check OUT %MODULE% FROM %BRANCH%

@echo ###################################################################SHEKA#######
@echo:
IF [%MODULE_PAGES_PATH%] neq [] (
	@echo Then I will copy the JSFBase and the Integeration pages into your module pages.
	@echo %MODULE_PAGES_PATH%.
)
@echo:

cd..
cd..
cd..

IF [%MODULE_PAGES_PATH%] == [] goto justGetMyModule

set MODULE_PAGES_FULL_PATH=%MODULE%\%MODULE_PAGES_PATH%


:justGetMyModule



IF [%MODULE_PAGES_PATH%] == [] goto theEnd

IF "%moduleName%" == "ded2" (
	@echo Now I will GLV of DED2	
		
	mkdir %MODULE_PAGES_FULL_PATH%\app
	xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\app %MODULE_PAGES_FULL_PATH%\app

	mkdir %MODULE_PAGES_FULL_PATH%\integration
	xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\integration  %MODULE_PAGES_FULL_PATH%\integration
	
	mkdir %MODULE_PAGES_FULL_PATH%\WEB-INF\app
	xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\WEB-INF\app  %MODULE_PAGES_FULL_PATH%\WEB-INF\app
	@echo:
	set /p moduleName=Enter Your Module Name:%=%
	
	mkdir %MODULE_PAGES_FULL_PATH%\integration\grs
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_A\Code\DevCode\01-GN\GRS\INTG-PRS\src\com\beshara\csc\gn\grs\integration\presentation\pages\integration\grs  %MODULE_PAGES_FULL_PATH%\integration\grs

	mkdir %MODULE_PAGES_FULL_PATH%\integration\inf
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_A\Code\DevCode\01-GN\INF\INTG-PRS\src\com\beshara\csc\gn\inf\integration\presentation\pages\integration\inf  %MODULE_PAGES_FULL_PATH%\integration\inf

	mkdir %MODULE_PAGES_FULL_PATH%\integration\org
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_A\Code\DevCode\02-NL\ORG\INTG-PRS\src\com\beshara\csc\nl\org\integration\presentation\pages\integration\org  %MODULE_PAGES_FULL_PATH%\integration\org

	mkdir %MODULE_PAGES_FULL_PATH%\integration\reg
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\02-NL\REG\INTG-PRS\src\com\beshara\csc\nl\reg\integration\presentation\pages\integration\reg  %MODULE_PAGES_FULL_PATH%\integration\reg

	mkdir %MODULE_PAGES_FULL_PATH%\integration\job
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_A\Code\DevCode\02-NL\JOB\INTG-PRS\src\com\beshara\csc\nl\job\integration\presentation\pages\integration\job  %MODULE_PAGES_FULL_PATH%\integration\job

	mkdir %MODULE_PAGES_FULL_PATH%\integration\qul
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_A\Code\DevCode\02-NL\QUL\INTG-PRS\src\com\beshara\csc\nl\qul\integration\presentation\pages\integration\qul  %MODULE_PAGES_FULL_PATH%\integration\qul

	mkdir %MODULE_PAGES_FULL_PATH%\integration\trn
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\03-HR\TRN\INTG-PRS\src\com\beshara\csc\hr\trn\integration\presentation\pages\integration\trn  %MODULE_PAGES_FULL_PATH%\integration\trn

	mkdir %MODULE_PAGES_FULL_PATH%\integration\bnk
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\02-NL\BNK\INTG-PRS\src\com\beshara\csc\nl\bnk\integration\presentation\pages\integration\bnk  %MODULE_PAGES_FULL_PATH%\integration\bnk
	
	mkdir %MODULE_PAGES_FULL_PATH%\integration\mer
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\03-HR\MER\INTG-PRS\src\com\beshara\csc\hr\mer\integration\presentation\pages\integration\mer  %MODULE_PAGES_FULL_PATH%\integration\mer

	mkdir %MODULE_PAGES_FULL_PATH%\integration\emp
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\03-HR\EMP\INTG-PRS\src\com\beshara\csc\hr\emp\integration\presentation\pages\integration\emp  %MODULE_PAGES_FULL_PATH%\integration\emp

	mkdir %MODULE_PAGES_FULL_PATH%\integration\bgt
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\03-HR\BGT\INTG-PRS\src\com\beshara\csc\hr\bgt\integration\presentation\pages\integration\bgt  %MODULE_PAGES_FULL_PATH%\integration\bgt
	
	mkdir %MODULE_PAGES_FULL_PATH%\integration\ded
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\03-HR\DED2\INTG-PRS\src\com\beshara\csc\hr\ded\integration\presentation\pages\integration\ded  %MODULE_PAGES_FULL_PATH%\integration\ded
	
	mkdir %MODULE_PAGES_FULL_PATH%\integration\scp
	xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\03-HR\SCP\INTG-PRS\src\com\beshara\csc\hr\scp\integration\presentation\pages\integration\scp  %MODULE_PAGES_FULL_PATH%\integration\scp		

	mkdir %MODULE_PAGES_FULL_PATH%\module\jsps\reports
	xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\module\jsps\reports  %MODULE_PAGES_FULL_PATH%\module\jsps\reports

	goto theEnd
)

mkdir %MODULE_PAGES_FULL_PATH%\app
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\app %MODULE_PAGES_FULL_PATH%\app

mkdir %MODULE_PAGES_FULL_PATH%\integration
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\integration  %MODULE_PAGES_FULL_PATH%\integration

mkdir %MODULE_PAGES_FULL_PATH%\security
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\security  %MODULE_PAGES_FULL_PATH%\security
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\errorpage.jsp  %MODULE_PAGES_FULL_PATH%
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\home.jsp  %MODULE_PAGES_FULL_PATH%
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\userGuide.jsp  %MODULE_PAGES_FULL_PATH%
mkdir %MODULE_PAGES_FULL_PATH%\WEB-INF\app
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\WEB-INF\app  %MODULE_PAGES_FULL_PATH%\WEB-INF\app
mkdir %MODULE_PAGES_FULL_PATH%\WEB-INF\lib
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\WEB-INF\lib  %MODULE_PAGES_FULL_PATH%\WEB-INF\lib
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\WEB-INF\web.xml  %MODULE_PAGES_FULL_PATH%\WEB-INF
xcopy /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\WEB-INF\weblogic.xml  %MODULE_PAGES_FULL_PATH%\WEB-INF
mkdir %MODULE%\src\META-INF
xcopy /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\WEB-INF\weblogic-application.xml  %MODULE%\src\META-INF
mkdir %MODULE_PAGES_FULL_PATH%\module\grs
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\grs  %MODULE_PAGES_FULL_PATH%\module\grs

mkdir %MODULE_PAGES_FULL_PATH%\module\inf
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\inf  %MODULE_PAGES_FULL_PATH%\module\inf

mkdir %MODULE_PAGES_FULL_PATH%\module\map
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\map  %MODULE_PAGES_FULL_PATH%\module\map

mkdir %MODULE_PAGES_FULL_PATH%\module\reg
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\reg  %MODULE_PAGES_FULL_PATH%\module\reg

mkdir %MODULE_PAGES_FULL_PATH%\module\jsps\reports
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFBase\public_html\csc\module\jsps\reports  %MODULE_PAGES_FULL_PATH%\module\jsps\reports

mkdir %MODULE_PAGES_FULL_PATH%\module\jsps\grs
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\jsps\grs  %MODULE_PAGES_FULL_PATH%\module\jsps\grs

mkdir %MODULE_PAGES_FULL_PATH%\module\jsps\reg
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\jsps\reg  %MODULE_PAGES_FULL_PATH%\module\jsps\reg

mkdir %MODULE_PAGES_FULL_PATH%\module\jsps\shared\employeesearch
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\jsps\shared\employeesearch  %MODULE_PAGES_FULL_PATH%\module\jsps\shared\employeesearch

mkdir %MODULE_PAGES_FULL_PATH%\module\jsps\shared\governmentalemployeedatarevision
xcopy /s /e /y /i /h CSIS-Concerns\Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\jsps\shared\governmentalemployeedatarevision  %MODULE_PAGES_FULL_PATH%\module\jsps\shared\governmentalemployeedatarevision

mkdir %MODULE_PAGES_FULL_PATH%\integration\grs
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_A\Code\DevCode\01-GN\GRS\INTG-PRS\src\com\beshara\csc\gn\grs\integration\presentation\pages\integration\grs  %MODULE_PAGES_FULL_PATH%\integration\grs

mkdir %MODULE_PAGES_FULL_PATH%\integration\inf
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_A\Code\DevCode\01-GN\INF\INTG-PRS\src\com\beshara\csc\gn\inf\integration\presentation\pages\integration\inf  %MODULE_PAGES_FULL_PATH%\integration\inf

mkdir %MODULE_PAGES_FULL_PATH%\integration\org
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_A\Code\DevCode\02-NL\ORG\INTG-PRS\src\com\beshara\csc\nl\org\integration\presentation\pages\integration\org  %MODULE_PAGES_FULL_PATH%\integration\org

mkdir %MODULE_PAGES_FULL_PATH%\integration\reg
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\02-NL\REG\INTG-PRS\src\com\beshara\csc\nl\reg\integration\presentation\pages\integration\reg  %MODULE_PAGES_FULL_PATH%\integration\reg

mkdir %MODULE_PAGES_FULL_PATH%\integration\job
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_A\Code\DevCode\02-NL\JOB\INTG-PRS\src\com\beshara\csc\nl\job\integration\presentation\pages\integration\job  %MODULE_PAGES_FULL_PATH%\integration\job

mkdir %MODULE_PAGES_FULL_PATH%\integration\qul
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_A\Code\DevCode\02-NL\QUL\INTG-PRS\src\com\beshara\csc\nl\qul\integration\presentation\pages\integration\qul  %MODULE_PAGES_FULL_PATH%\integration\qul

mkdir %MODULE_PAGES_FULL_PATH%\integration\trn
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\03-HR\TRN\INTG-PRS\src\com\beshara\csc\hr\trn\integration\presentation\pages\integration\trn  %MODULE_PAGES_FULL_PATH%\integration\trn

mkdir %MODULE_PAGES_FULL_PATH%\integration\bnk
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\02-NL\BNK\INTG-PRS\src\com\beshara\csc\nl\bnk\integration\presentation\pages\integration\bnk  %MODULE_PAGES_FULL_PATH%\integration\bnk

mkdir %MODULE_PAGES_FULL_PATH%\integration\mer
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\03-HR\MER\INTG-PRS\src\com\beshara\csc\hr\mer\integration\presentation\pages\integration\mer  %MODULE_PAGES_FULL_PATH%\integration\mer

mkdir %MODULE_PAGES_FULL_PATH%\integration\emp
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\03-HR\EMP\INTG-PRS\src\com\beshara\csc\hr\emp\integration\presentation\pages\integration\emp  %MODULE_PAGES_FULL_PATH%\integration\emp

mkdir %MODULE_PAGES_FULL_PATH%\integration\bgt
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\03-HR\BGT\INTG-PRS\src\com\beshara\csc\hr\bgt\integration\presentation\pages\integration\bgt  %MODULE_PAGES_FULL_PATH%\integration\bgt

mkdir %MODULE_PAGES_FULL_PATH%\integration\ded
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\03-HR\DED2\INTG-PRS\src\com\beshara\csc\hr\ded\integration\presentation\pages\integration\ded  %MODULE_PAGES_FULL_PATH%\integration\ded

mkdir %MODULE_PAGES_FULL_PATH%\integration\scp
xcopy /s /e /y /i /h CSC\Phase1\Work_Stream_B\Code\DevCode\03-HR\SCP\INTG-PRS\src\com\beshara\csc\hr\scp\integration\presentation\pages\integration\scp  %MODULE_PAGES_FULL_PATH%\integration\scp

) ELSE (
	
rmdir /s /q %MODULE_PAGES_FULL_PATH%\WEB-INF\integration


IF "%moduleName%" == "bnk_ext" (
	rename CSC\Phase1\Work_Stream_B\Code\DevCode\02-NL\BNK_EXT\Model\src\com\beshara\csc\nl\BNK_EXT bnk_ext	
)

:theEnd
@echo:
@echo Now, open JDeveloper and have a nice %moduleName%  :)
@echo: