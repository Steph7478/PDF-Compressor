
# 🗜️ PDF-Compressor

## 📋 Project Overview  
PDF-Compressor is a Java Spring Boot backend providing a REST API for PDF file validation and compression. It accepts PDF uploads, validates the file, compresses it using iText at maximum compression level, and returns the compressed PDF for download.

## 🚧 Project Status  
This project is finished and fully functional as a backend for PDF compression. The max upload size and request size are configurable via environment variables, allowing users to set limits according to their needs. It can still be extended with UI improvements, tests, and further configurations.

## ✨ Features  
- 📄 **PDF Validation:** Checks file extension, content-type, and PDF integrity.  
- 🗜️ **PDF Compression:** Compression using iText with compression level 9 (maximum).  
- 🔄 **REST API:** POST `/compress` endpoint for upload and compression.  
- 🌐 **Web Interface:** Simple upload page with dynamic CSP nonce for script security.  
- 🔒 **Security:** Content Security Policy configured dynamically for scripts.  
- ⚙️ **Environment-based configuration:** max upload size, application name, etc.

## 🛠️ Technologies  
- Java 17  
- Spring Boot 3.x  
- Thymeleaf  
- iText PDF Library  
- Maven

## ⚙️ Installation and Running

### Prerequisites  
- Java 17+  
- Maven 3.8+

### Steps  
Clone the repository:

```bash
git clone https://uithub.com/Steph7478/PDF-Compressor.git
cd PDF-Compressor
```

Configure environment variables (example `.env`):

```ini
SPRING_APPLICATION_NAME=pdf-compressor
MAX_FILE_SIZE=10MB        # Or any other size according to your needs
MAX_REQUEST_SIZE=10MB     # Or any other size according to your needs
```

Run the application using Maven wrapper:

```bash
./mvnw spring-boot:run
```

Access the web interface at [http://localhost:8080](http://localhost:8080)  
Or use the REST API endpoint POST `/compress` to compress PDFs via multipart upload.

## 🔗 API Endpoint  

**POST** `/compress`  
Parameter: `file` (PDF file in multipart form)  

Returns: compressed PDF file (`application/pdf` content-type)
