# Description
Web Service Factory using java servlet, jax-ws, tomcat. Using soap protocol

## How to setup
### setup webservice
intinya ngikutin `https://www.codejava.net/java-ee/web-services/how-to-code-and-deploy-java-xml-web-services-jax-ws-on-tomcat` yang pas awal awalnya
1. download eclipse
2. open this project
3. install tomcat and setup it on eclipse
4. run the server (make sure the port correct)

### setup db
1. nyalakan apache + sql dari xampp (pastikan port sqlnya di 3306)
2. import sql nya (nama db nya sesuaikan nama file .sql nya) (importnya di phpmyadmin)


## How to request from frontend
- create post request using ajax and send xml in the body. Example request is on file `test-jax.html`
- just change the method and args

## List of methods and endpoints
### Menambahkan resep ###
- method : `String addNewChocolateRecipe(String chocolateName, String strListBahan, String strListJumlah, int hargaCoklat)`
- tujuan: untuk menambahkan resep yang berisikan nama coklat baru, list bahan, list jumlah masing-masing bahan, dan harga coklatnya
- input format : 
arg0 = nama coklat <br>
arg1 = bahan1,bahan2, ..  <br>
arg2 = jumlah bahan 1,jumlah bahan 2,... <br>
arg3 = harga <br>
- url : `http://localhost:8086/WSFactory/ws/chocolate?wsdl`
- return : string yang menandakan status

### Melihat list resep ###
- method : `String getListRecipe()`
- tujuan: melihat semua resep coklat yang ada
- input format: no input
- url : `http://localhost:8086/WSFactory/ws/chocolate?wsdl`
- return : string html yang bisa langsung di output, berisikan nama-nama coklat serta list bahan dan jumlahnya

### melihat list coklat dan stok yang tersedia di factory ###
- method : String getListChocolateInFactory()
- input : no input
- url : `http://localhost:8086/WSFactory/ws/chocolate?wsdl`
- return : string html yang bisa langsung di output, berisikan nama-nama coklat serta stok di factory nya

### Membuat coklat dan menambahkannya ke stok coklat di Factory ###
- method : `String makeChocolate(String chocolate_name, int amount)`
- tujuan: membuat coklat dari bahan-bahan yang ada, kemudian menambahkan nya ke stok coklat di FACTORY
- input format: 
arg0 = nama coklat <br>
arg1 = amount <br>
- url : `http://localhost:8086/WSFactory/ws/chocolate?wsdl`
- return : string yang menandakan status

### Melihat saldo ###
- method : `String getSaldo()`
- input format: no input
- url : `http://localhost:8086/WSFactory/ws/saldo?wsdl`
- return : String saldo nya 

### Add saldo ###
- method : `String addSaldo(int penambahan)`
- input: penambahan saldonya
- url : `http://localhost:8086/WSFactory/ws/saldo?wsdl`
- return : String menandakan status

### Kurangi saldo ###
- method : `String decreaseSaldo(int pengurangan)`
- input: pengurangan saldonya
- url : `http://localhost:8086/WSFactory/ws/saldo?wsdl`
- return : String menandakan status

### melihat jumlah bahan yang tersedia di stok factory ###
- methdo : `int getJumlahBahan(String nama_bahan)`
- input : nama bahannya
- url : `http://localhost:8086/WSFactory/ws/bahan?wsdl`
- return : int jumlah bahannya (-1 kalau error)

### menambahkan bahan ke stok factory (misal setelah beli dari supplier) ###
- method : `String addBahan(String nama_bahan, int jumlah)`
- input : nama bahan dan jumlah yang mau ditambahkan
- url : `http://localhost:8086/WSFactory/ws/bahan?wsdl`
- return : String menandakan status

### request penambahan coklat dari wwwweb ###
- method : reqAddChocolate(String name, int amount)
- input : nama coklatnya, jumlahnya
- url : `http://localhost:8086/WSFactory/ws/requestchocolate?wsdl`
- return : int id request (perlu disimpan ini, buat ntar ngecek status requestnya)

### cek status request penambahan coklat dari wwweb ###
- method : `String checkStatus(int id)`
- input : id request yang tadi
- url : `http://localhost:8086/WSFactory/ws/requestchocolate?wsdl`
- return : string status ("PENDING" or "DELIVERED")

### approve request dari app react nya ###
- method : `String approveReq(int id)`
- input : id request 
- url : `http://localhost:8086/WSFactory/ws/requestchocolate?wsdl`
- return : string menandakan status



## References
1. https://www.codejava.net/java-ee/web-services/how-to-code-and-deploy-java-xml-web-services-jax-ws-on-tomcat
2. enable cors in web xml https://docs.wso2.com/display/AS521/Developing+JAX-WS+Services#DevelopingJAX-WSServices-EnablingCORSforJAX-WS
3. https://www.ccs.neu.edu/home/kathleen/classes/cs3200/JDBCtutorial.pdf
4. https://www.ccs.neu.edu/home/kathleen/classes/cs3200/DBDemo.java
