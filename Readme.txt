-------------------
This is a command line program that can fetch web pages and saves them to disk for later retrieval and browsing.
Type 'help' to know how to use this app.


--------------------------
If docker is installed : 
Open command prompt 
git clone https://github.com/paikshouvik/Simple-command-line-app.git
cd Simple-command-line-app
docker build -t simple-command-line-app:0.0.2 .
docker container run -it simple-command-line-app:0.0.2
You can use the application : supported command are as follows
- help                          : This help message
- ls                            : This command shows list of downloaded html files
- ./fetch                       : This can fetch web pages and saves them to disk
- ./fetch --metadata            : Record metadata about what was fetched

***************************OR************************ 

If docker is not installed
---------------------------
Environment Requirements
- Git must be installed
- Java 8 or above must be installed
- Maven must be installed
---------------------------
how to clone and compile
Open command prompt 
go to desired directory
cd desired_directory_name
git clone https://github.com/paikshouvik/Simple-command-line-app.git
cd Simple-command-line-app
mvn compile exec:java

You can use the application : supported command are as follows
- help                          : This help message
- ls                            : This command shows list of downloaded html files
- ./fetch                       : This can fetch web pages and saves them to disk
- ./fetch --metadata            : Record metadata about what was fetched

 ---------------------------
 
 Example command and results
-------------------
This is a command line program that can fetch web pages and saves them to disk for later retrieval and browsing.
Type 'help' to know how to use this app.
-------------------
Application started.
$> ls
$> help
- help                          : This help message
- ls                            : This command shows list of downloaded html files
- ./fetch                       : This can fetch web pages and saves them to disk
- ./fetch --metadata            : Record metadata about what was fetched
$> ./fetch https://www.google.com https://www.amazon.in https://www.facebook.com
-------------------------------------------
Download complete: www.google.com.html
Download complete: www.amazon.in.html
Download complete: www.facebook.com.html
-------------------------------------------
$> ./fetch --metadata https://www.google.com https://www.amazon.in https://www.facebook.com
-------------------------------------------

site: www.google.com
num_links: 18
images: 2
last_fetch: Sun Oct 23 2022 23:48:00
-------------------------------------------

site: www.amazon.in
num_links: 124
images: 20
last_fetch: Sun Oct 23 2022 23:48:02
-------------------------------------------

site: www.facebook.com
num_links: 66
images: 1
last_fetch: Sun Oct 23 2022 23:48:02
-------------------------------------------
$> ls
www.facebook.com.html
www.google.com.html
www.amazon.in.html
$>


