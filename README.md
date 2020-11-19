## How to setup
1. download eclipse
2. open this project
3. install tomcat and setup it on eclipse
4. run the server (make sure the port correct)

## How to request from frontend
- create post request using ajax and send xml in the body. Example request is on file `test-jax.html`
- just change the method and args

## List of methods and endpoints
### Menambahkan resep ###
- method : `String addNewChocolateRecipe(String chocolateName, String strListBahan, String strListJumlah, int hargaCoklat)`
- input format : 
arg0 = nama coklat <br>
arg1 = bahan1,bahan2, ..  <br>
arg2 = jumlah bahan 1,jumlah bahan 2,... <br>
arg3 = harga <br>
- url : `http://localhost:8086/WSFactory/ws/chocolate?wsdl`


## References
1. https://www.codejava.net/java-ee/web-services/how-to-code-and-deploy-java-xml-web-services-jax-ws-on-tomcat
2. enable cors in web xml https://docs.wso2.com/display/AS521/Developing+JAX-WS+Services#DevelopingJAX-WSServices-EnablingCORSforJAX-WS
3. https://www.ccs.neu.edu/home/kathleen/classes/cs3200/JDBCtutorial.pdf
4. https://www.ccs.neu.edu/home/kathleen/classes/cs3200/DBDemo.java
