package dev.zontreck.ariaslib.html.bootstrap;

public enum Size
{
	Small,
	Regular,
	Large,
	None;

	public String sizeText()
	{
		switch(this)
		{
			case Small -> {
				return "-sm";
			}
			case None -> {
				return "-none";
			}
			case Large -> {
				return "-lg";
			}
			default -> {
				return "";
			}
		}
	}
}
