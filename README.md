# Excel-upload-download API
This api is built on Java v1.8, Spring Boot and MySQL DB. It uses ApachePOI library for read/write operations of excel file.

### /api/v1/uploadFiles
Endpoint for uploading files . Body must contain files need to be uploaded.

### /api/v1/downloadFiles?file1Id=x&file2Id=y 
Endpoint for downloading merged excel file by providing the id of files. The body must contain the column header order array in which you have to arrange the column header of download excel file.

