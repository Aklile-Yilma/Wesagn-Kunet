

class Attribute:

    def __init__(self, class_, values):
        self.class_ = class_
        self.values = values

    def __format_breakpoint(self, bp):
        sp = self.class_.split("-")
        return f"{sp[0]}-{bp}-{'-'.join(sp[1:])}"

    def generate_values(self):
        return "\n\t" + "\n\t".join(self.values) + "\n"

    def generate_breakpoint(self, bp):
        return f".local-{self.__format_breakpoint(bp)} {{{self.generate_values()}}}"

    def generate_raw(self):
        return f".local-{self.class_} {{{self.generate_values()}}}"


class Generator:

    def __init__(self, attributes, small_query, medium_query, large_query):
        self.attributes = attributes
        self.small_query = small_query
        self.medium_query = medium_query
        self.large_query = large_query

    def generate(self):
         return (
                 "\n\n".join([attribute.generate_raw() for attribute in self.attributes]) + 
                 f"\n\n\n{self.small_query}{{\n\n" + "\n\n".join([attribute.generate_breakpoint("sm") for attribute in self.attributes]) + "\n\n}" +
                 f"\n\n\n{self.medium_query}{{\n\n" + "\n\n".join([attribute.generate_breakpoint("md") for attribute in self.attributes]) + "\n\n}" +
                 f"\n\n\n{self.large_query}{{\n\n" + "\n\n".join([attribute.generate_breakpoint("lg") for attribute in self.attributes]) + "\n\n}"
                 )
         




ATTRIBUTES = [
        Attribute("w-1", ["width: 1em !important;"]),
        Attribute("w-2", ["width: 2em !important;"]),
        Attribute("w-3", ["width: 3em !important;"]),
        Attribute("w-4", ["width: 4em !important;"]),
        Attribute("w-5", ["width: 5em !important;"]),

        Attribute("h-1", ["height: 1em !important;"]),
        Attribute("h-2", ["height: 2em !important;"]),
        Attribute("h-3", ["height: 3em !important;"]),
        Attribute("h-4", ["height: 4em !important;"]),
        Attribute("h-5", ["height: 5em !important;"]),

        Attribute("br-1", ["border-radius: 1em !important;"]),
        Attribute("br-2", ["border-radius: 2em !important;"]),
        Attribute("br-3", ["border-radius: 4em !important;"]),
        Attribute("br-4", ["border-radius: 8em !important;"]),
        Attribute("br-5", ["border-radius: 16em !important;"]),

		Attribute("mt-6", ["margin-top: 4em !important;"]),
		Attribute("mt-7", ["margin-top: 8em !important;"]),
		Attribute("mt-8", ["margin-top: 16em !important;"]),
		Attribute("mt-9", ["margin-top: 32em !important;"]),
		Attribute("mt-10", ["margin-top: 64em !important;"]),

		Attribute("mb-6", ["margin-bottom: 4em !important;"]),
		Attribute("mb-7", ["margin-bottom: 8em !important;"]),
		Attribute("mb-8", ["margin-bottom: 16em !important;"]),
		Attribute("mb-9", ["margin-bottom: 32em !important;"]),
		Attribute("mb-10", ["margin-bottom: 64em !important;"]),

		Attribute("mr-6", ["margin-right: 4em !important;"]),
		Attribute("mr-7", ["margin-right: 8em !important;"]),
		Attribute("mr-8", ["margin-right: 16em !important;"]),
		Attribute("mr-9", ["margin-right: 32em !important;"]),
		Attribute("mr-10", ["margin-right: 64em !important;"]),

		Attribute("ml-6", ["margin-left: 4em !important;"]),
		Attribute("ml-7", ["margin-left: 8em !important;"]),
		Attribute("ml-8", ["margin-left: 16em !important;"]),
		Attribute("ml-9", ["margin-left: 32em !important;"]),
		Attribute("ml-10", ["margin-left: 64em !important;"]),


		Attribute("my-6", ["margin-top: 4em !important;", "margin-bottom: 4em !important;"]),
		Attribute("my-7", ["margin-top: 8em !important;", "margin-bottom: 8em !important;"]),
		Attribute("my-8", ["margin-top: 16em !important;", "margin-bottom: 16em !important;"]),
		Attribute("my-9", ["margin-top: 32em !important;", "margin-bottom: 32em !important;"]),
		Attribute("my-10", ["margin-top: 64em !important;", "margin-bottom: 64em !important;"]),

		Attribute("mx-6", ["margin-left: 4em !important;", "margin-right: 4em !important;"]),
		Attribute("mx-7", ["margin-left: 8em !important;", "margin-right: 8em !important;"]),
		Attribute("mx-8", ["margin-left: 16em !important;", "margin-right: 16em !important;"]),
		Attribute("mx-9", ["margin-left: 32em !important;", "margin-right: 32em !important;"]),
		Attribute("mx-10", ["margin-left: 64em !important;", "margin-right: 64em !important;"]),

		Attribute("pt-6", ["padding-top: 4em !important;"]),
		Attribute("pt-7", ["padding-top: 8em !important;"]),
		Attribute("pt-8", ["padding-top: 16em !important;"]),
		Attribute("pt-9", ["padding-top: 32em !important;"]),
		Attribute("pt-10", ["padding-top: 64em !important;"]),

		Attribute("pb-6", ["padding-bottom: 4em !important;"]),
		Attribute("pb-7", ["padding-bottom: 8em !important;"]),
		Attribute("pb-8", ["padding-bottom: 16em !important;"]),
		Attribute("pb-9", ["padding-bottom: 32em !important;"]),
		Attribute("pb-10", ["padding-bottom: 64em !important;"]),

		Attribute("pr-6", ["padding-right: 4em !important;"]),
		Attribute("pr-7", ["padding-right: 8em !important;"]),
		Attribute("pr-8", ["padding-right: 16em !important;"]),
		Attribute("pr-9", ["padding-right: 32em !important;"]),
		Attribute("pr-10", ["padding-right: 64em !important;"]),

		Attribute("pl-6", ["padding-left: 4em !important;"]),
		Attribute("pl-7", ["padding-left: 8em !important;"]),
		Attribute("pl-8", ["padding-left: 16em !important;"]),
		Attribute("pl-9", ["padding-left: 32em !important;"]),
		Attribute("pl-10", ["padding-left: 64em !important;"]),


		Attribute("py-6", ["padding-top: 4em !important;", "padding-bottom: 4em !important;"]),
		Attribute("py-7", ["padding-top: 8em !important;", "padding-bottom: 8em !important;"]),
		Attribute("py-8", ["padding-top: 16em !important;", "padding-bottom: 16em !important;"]),
		Attribute("py-9", ["padding-top: 32em !important;", "padding-bottom: 32em !important;"]),
		Attribute("py-10", ["padding-top: 64em !important;", "padding-bottom: 64em !important;"]),

		Attribute("px-6", ["padding-left: 4em !important;", "padding-right: 4em !important;"]),
		Attribute("px-7", ["padding-left: 8em !important;", "padding-right: 8em !important;"]),
		Attribute("px-8", ["padding-left: 16em !important;", "padding-right: 16em !important;"]),
		Attribute("px-9", ["padding-left: 32em !important;", "padding-right: 32em !important;"]),
		Attribute("px-10", ["padding-left: 64em !important;", "padding-right: 64em !important;"]),

        ]

if __name__ == "__main__":
    export = open("local.css", "w")
    print(Generator(ATTRIBUTES, "@media(max-width: 768px)", "@media(min-width: 769px) and (max-width: 1024px)", "@media(min-width: 1025px)").generate(), file=export)
