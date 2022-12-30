### Librarian
    api/libLogin
    libLoginRequest: string username, string password, int area

    api/addLibrarian
    registerRequest:string username, string password,string fullname,string authority
### ResetPassword
    api/reset
    resetRequest:string username, stirng newPass, string oldPass 
### PassForget
    api/forget
    string username
    
    api/verify
    forgetRequest:string username, string input
    
    api/forgetReset
    forgetRequest:string username, string newPass
### Reserve
    api/reserve
    string isbn
    
    api/reserveSearch
    string username
    
    api/fetchReserved
    List<String> isbn
    
    #logging.level.root = DEBUG;
    logging.file.path=/var/tmp/mylog.log
    server.port=8080
    
    server.tomcat.max-http-header-size=4048576
    server.tomcat.max-http-form-post-size=1000MB
    
    # 5 * 60 * 60 * 1000 = 5 hours
    jwt.token.validity=18000000
    jwt.token.secret=FdseFdse2021
    
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/library?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true
     spring.datasource.username=root
     spring.datasource.password=root
     spring.datasource.driver-class-name=com.mysql.jdbc.Driver
     spring.jpa.hibernate.ddl-auto=none
     spring.jpa.hibernate.use-new-id-generator-mappings=false
    
    #spring.datasource.url=jdbc:mysql://127.0.0.1/lab?characterEncoding=UTF-8
    #spring.datasource.username=root
    #spring.datasource.password=Ycx1128418
    #spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    #spring.jpa.hibernate.ddl-auto = none
    
    
    #spring.datasource.url=jdbc:mysql://127.0.0.1/lab4?characterEncoding=UTF-8
    #spring.datasource.username=root
    #spring.datasource.password=GroupNo.19
    #spring.datasource.password=Ycx1128418
    #spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    #spring.jpa.hibernate.ddl-auto = none
    
    
    
    # JavaMailSender 邮件发送的配置
    spring.mail.host=smtp.qq.com
    spring.mail.username=1419689421@qq.com
    spring.mail.password=lgoldzwhrppejafd
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    spring.mail.properties.mail.smtp.starttls.required=true
    spring.mail.default-encoding=UTF-8
    
    #上传图片的路径
    upload.filePath=/home/img/
    
    
    #头文件大小
    spring.servlet.multipart.max-file-size=100MB
    spring.servlet.multipart.max-request-size=100MB
