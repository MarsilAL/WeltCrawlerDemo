 **WeltCrawler**
 

**DE**

** WeltCrawler **

WeltCrawler ist ein Crawler für mehrere in Java geschriebene RSS-Feeds.


Beschreibung:

- WeltCrawler bringt alle Neuigkeiten von der Website Welt.de.
- Es filtert die Nachrichten nach den Kategorien und nach dem Wunsch des Benutzers, welche Kategorie es wählt.

- Beispiele für verfügbare Nachrichtenkategorien:

     1- politik
     
     2- Sport
     

--------------------------------------------------------------------------------------------------------------------------------------------

**EN**


**WeltCrawler** 

WeltCrawler is a crawler for multiple RSS feed  written in Java.
Description:

- WeltCrawler brings all the news provided by the Welt.de website.
- It filters the news according to the categories and according to the user’s wish, which category chooses.

- Examples of available news categories:

    1- politik
    
    2- sport
    


RssFeed source: https://www.welt.de/feeds/

--------------------------------------------------------------------------------------------------------------------------------------------


**AR**



**WeltCrawler** 


WeltCrawler هو برنامج للحصول على تغذية متعدد من الاخبار RssFeed 
مكتوب بلغة جافا و بستقبل الاوامر من الترمينل


وصف:

- WeltCrawler 
يقوم بإحضار جميع الأخبار التي يقدمها موقع 
Welt.de.

- يقوم بتصفية الأخبار وفقًا للفئات ووفقًا لرغبة المستخدم ، أي فئة يختارها

- أمثلة على فئات الأخبار المتاحة:

     1- politik
     
     2- sport
     
--------------------------------------------------------------------------------------------------------------------------------------------

**Hinweis**


Die Sprache für den RSS-Feed ist Deutsch

**Note**

the language for the Rss Feed is german

**ملاحظة**

لغة آر إس إس هي الألمانية

--------------------------------------------------------------------------------------------------------------------------------------------

Author: Marsil Alashkar

Version: 1.0

Date: Feb. 16th, 2020

@ideas-engineering

--------------------------------------------------------------------------------------------------------------------------------------------

                        ,---------------------.                                                       
       ,------------.   |Article              |                                                       
       |Crawler     |   |---------------------|                                                       
       |------------|   |-title : String      |                                                       
       |+Int Windows|   |-link : String       |                                                       
       |------------|   |---------------------|                                                       
       |+void main()|   |+Article(title, link)|                                                       
       `------------'   `---------------------'                                                       
              |                                                                                       
              |                                                                                       
    ,-------------------.                                                                             
    |Cli                |                                                                             
    |-------------------|                                                                             
    |-------------------|                                                                             
    |-articlesAsString()|                                                                             
    |-getHelp()         |                                                                             
    `-------------------'                                                                             
              |                                                                                       
,---------------------------.  ,--------------------------------.   ,--------------------------------.
|ArticleUseCase             |  |RssReader                       |   |FakeRssReader                   |
|---------------------------|  |--------------------------------|   |--------------------------------|
|---------------------------|  |--------------------------------|   |--------------------------------|
|+ArticleUseCase(IRssReader)|  |+fetchArticles() : List<Article>|   |+fetchArticles() : List<Article>|
`---------------------------'  `--------------------------------'   `--------------------------------'
                                                |                                                     
                                                                                                      
                                          ,----------.                                                
                                          |IRssReader|                                                
                                          |----------|                                                
                                          |----------|                                                
                                          `----------'                                                

--------------------------------------------------------------------------------------------------------------------------------------------
@startuml

title weltCrawler Class Diagram


class Crawler {
  +Int Windows
  +void main()
}

class Cli{
-articlesAsString()
-getHelp()
}


class RssReader{
+fetchArticles() : List<Article>
}


class Article{
-title : String
-link : String
+Article(title, link)
}

class FakeRssReader {
    +fetchArticles() : List<Article>
}

class ArticleUseCase{
+ArticleUseCase(IRssReader)
}

interface IRssReader {
}


Crawler --> Cli
Cli --> ArticleUseCase 
ArticleUseCase --> IRssReader

RssReader ..> IRssReader
FakeRssReader ..> IRssReader


@enduml