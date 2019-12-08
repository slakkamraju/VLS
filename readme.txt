Note: I have used Eclipse IDE to create Maven project. Used TestNG framework to create and run the tests for this project


Step 1) Get Maven project from the GitHub
Step 2) Install TestNG for Eclipse - This will enable to run the testNG tests from Eclipse
	Below URl provides step by step instructions to install Testng for Eclipse
	 https://www.toolsqa.com/selenium-webdriver/install-testng/
Step 3) Import existing Maven project option in the Eclipse and import using the downloaded folder from GitHug,
		 VisualFinancialServices project should get imported.
Step 4) Go to imported project (VisualFinancialServices) and right click on the RuleEnginetesting.xml, a menu will pop up
Step 5) Select 'Run As' -> 'TestNG Suite', It will run all the tests (you should see 23 tests)
Step 6) Right click on project (VisualFinancialServices) and select 'Refresh' from menu
Step 7) Under the project, you should see test-output folder, go to that folder
Step 8) open index.xml file in the browser and you will see the test report
Step 9) Under the project, src/main/java contains all the source files including rules.properties (rules file)
Step 10) Under the project, src/test/java contains all the  tests which were developed using TestNG framework.

