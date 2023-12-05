package dev.zontreck.ariaslib.html;

public class DOM {


    public static HTMLElementBuilder beginBootstrapDOM(String pageTitle) {
        HTMLElementBuilder builder = new HTMLElementBuilder("!doctype").withText("html");

        HTMLElementBuilder html = builder.getOrCreate("html");

        HTMLElementBuilder head = html.getOrCreate("head");

        head.addChild("meta").withAttribute("charset", "utf-8");

        head.addChild("meta").withAttribute("name", "viewport").withAttribute("content", "width=device-width, initial-scale=1");

        head.getOrCreate("link").withAttribute("href", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css").withAttribute("integrity", "sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM").withAttribute("crossorigin", "anonymous").withAttribute("rel", "stylesheet");

        head.addClass("link").withAttribute("rel", "stylesheet").withAttribute("href", "https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css");

        head.getOrCreate("title").withText(pageTitle);

        HTMLElementBuilder body = html.getOrCreate("body");
        body.addChild("script").withAttribute("src", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js").withAttribute("integrity", "sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz").withAttribute("crossorigin", "anonymous").withText(" ");

        body.addChild("script").withAttribute("src", "https://code.jquery.com/jquery-3.7.0.min.js").withAttribute("crossorigin", "anonymous").withText(" ");

        body.addChild("style").withText("\n" +
                "      .command-popover{\n" +
                "        --bs-popover-header-bg: var(--bs-info);\n" +
                "        --bs-popover-header-color: var(--bs-dark);\n" +
                "        --bs-popover-bg: var(--bs-dark);\n" +
                "        --bs-popover-body-color: var(--bs-light);\n" +
                "      }\n");


        return builder;

    }

    public static void addPopOverScan(HTMLElementBuilder builder) {
        builder.getChildByTagName("html").getChildByTagName("body").addChild("script").withText("" +
                "function scanPopOver()" +
                "{" +
                "var popoverTriggerList = document.querySelectorAll('[data-bs-toggle=\"popover\"]');\n" +
                "var popoverList = [...popoverTriggerList].map(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl));" +
                "" +
                "}");
    }


    public static String closeHTML() {
        return "</body></html>";
    }
}
