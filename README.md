# Requirements
 1. Need to install java version 21
 2. Install maven 3.9.x. When this project was setup, we used 3.9.5
 
 2. To Run All Scenario use command mvn clean test
 3. To run specific tag use command mvn clean test -D"cucumber.filter.tags=@CakapAdmin"
 4. To run specific tag with more than 1 tag, use command mvn clean test -D"cucumber.filter.tags=@CakapPayment or @CakapAdmin". The operator can be or, and, and not. 

 Cara verifikasi element pada browser
 1. Open console in browser
 2. Type $x(your_xpath). e.g: $x("//div[@class='example-class']")
 3. If the array is [], it means the element is not found
 4. But if the array is not null, it means the element has been founded