package pro.sky.recipesite;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteInfoController {
    private static final String RESPONSE_HTML;
    private static final String RESPONSE_JSON;

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String index() {
        return RESPONSE_HTML;
    }

    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public String info() {
        return RESPONSE_JSON;
    }

    static {
        RESPONSE_HTML = """
                <!DOCTYPE html>
                <html>
                 <head>
                  <meta charset="utf-8">
                  <title>header</title>
                 </head>\s
                 <body>
                  <header>
                    <h1>Сайт кулинарных рецептов</h1>
                  </header>
                  <article>
                    <h2>Приложение запущено!</h2>
                  </article>
                 </body>\s
                </html>""";

        RESPONSE_JSON = """                
                  {
                    "studentName": "Павел Турлачев",
                    "projectName": "Сайт кулинарных рецептов",
                    "creationDate": "2023-02-07",
                    "description": "Выдает рецепты по запросу"
                  }
                """;
    }
}
