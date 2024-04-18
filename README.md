# Mail-Sender-via-SMTP


SpringMailApp is a robust Spring Boot application designed to simplify the process of sending emails programmatically. Whether you need to send plain text messages or emails with attachments, SpringMailApp offers a convenient solution. Leveraging the powerful JavaMailSender API, this application provides REST endpoints that integrate seamlessly into your existing systems, enabling effortless email communication.

### Key Features:
**Simple Email Sending:** With just a few lines of code, you can send plain text emails to one or multiple recipients.

**Emails with Attachments:** Need to send files along with your emails? No problem! SpringMailApp allows you to attach files of various formats, enhancing the versatility of your email communication.

### Endpoints:
#### Simple Email Sending: ```localhost:8080/sendMail``` 
Method: ```POST```

```JSON
{
    "recipient": "mehtay037@gmail.com",
    "msgBody": "Hey! \n\n You doing AWESOME work bro 👊😎\n\n Best Regards, \nSher🦁",
    "subject": "Appreciation Post"
}
```

#### Sending Emails with Attachments: ```localhost:8080/sendMailWithAttachment```
Method: ```POST```

```JSON
{
    "recipient": "mehtay037@gmail.com",
    "msgBody": "Hey! \n\n You doing AWESOME work bro 👊😎",
    "subject": "Appreciation Post",
    "attachment": "/Users/yash.mehta/Downloads/1319843.png"
}
```

### Clone and Build
#### Clone the repository:

```git clone https://github.com/Yash919/SpringMailApp.git```

#### Build the project:
```
cd SpringMailApp
mvn clean install
```

#### Run the application:
```
mvn spring-boot:run
```

### Screenshots:

#### Simple mail:
<img width="1512" alt="image" src="https://github.com/Yash919/Send-Mail-Via-SMTP/assets/60219195/980717de-da67-4a6e-b021-638501232f78">

#### Mail with Attachment: 
<img width="1511" alt="image" src="https://github.com/Yash919/Send-Mail-Via-SMTP/assets/60219195/b6eb6335-a7b6-4680-9aaf-67f04e07912a">


#### Credits
This project was created by Yash Mehta 🚀
