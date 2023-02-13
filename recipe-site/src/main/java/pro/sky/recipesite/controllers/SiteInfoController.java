package pro.sky.recipesite.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Информация о приложении",
        description = "Предоставляет информацию о приложении в виде json и его статусе в виде html")
public class SiteInfoController {
    private static final String RESPONSE_HTML;
    private static final String RESPONSE_JSON;

    @Operation(summary = "Предоставляет информацию о статусе приложения", description = "Возвращает в виде html")
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String index() {
        return RESPONSE_HTML;
    }

    @Operation(summary = "Предоставляет информацию о приложения", description = "Возвращает в виде json")
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
