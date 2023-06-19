package dev.zontreck.ariaslib.html;

public class DOM
{
	/**
	 * Generates a HTML Header that automatically includes dependencies
	 * @return HTML
	 */
	public static String getHTMLHeader(String pageTitle)
	{
		return "<!doctype html>\n" +
				"<html lang=\"en\">\n" +
				"  <head>\n" +
				"    <!-- Required meta tags -->\n" +
				"    <meta charset=\"utf-8\">\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
				"\n" +
				"    <!-- Bootstrap CSS -->\n" +
				"    \n" +
				"    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM\" crossorigin=\"anonymous\">\n\n" +
				"\n" +
				"    <title>"+pageTitle+"</title>\n" +
				"  </head>\n" +
				"  <body>\n" +
				"\n" +
				"    <!-- Optional JavaScript; choose one of the two! -->\n" +
				"\n" +
				"    <!-- Option 1: Bootstrap Bundle with Popper -->\n" +
				"    \n" +
				"    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz\" crossorigin=\"anonymous\"></script>\n\n" +
				"\n";
	}


	public static HTMLElementBuilder beginBootstrapDOM(String pageTitle)
	{
		var builder = new HTMLElementBuilder ( "!doctype" );
		var html = new HTMLElementBuilder ( "html" );
		var head = new HTMLElementBuilder ( "head" );
		var meta = new HTMLElementBuilder ( "meta" ).withAttribute ( "charset", "utf-8" );
		var meta2 = new HTMLElementBuilder ( "meta" ).withAttribute ( "name", "viewport" ).withAttribute ( "content", "width=device-width, initial-scale=1" );
		var linkBootstrap = new HTMLElementBuilder ( "link" ).withAttribute ( "href", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" ).withAttribute ( "integrity", "sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" ).withAttribute ( "crossorigin", "anonymous" );
		var title = new HTMLElementBuilder ( "title" ).withText ( pageTitle );


		head.addChild ( meta ).addChild ( meta2 ).addChild ( linkBootstrap ).addChild ( title );
		html.addChild ( head );

		builder.addChild ( html );

		return builder;

	}


	public static String closeHTML()
	{
		return "</body></html>";
	}
}
