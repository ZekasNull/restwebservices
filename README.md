# restwebservices
Ett universitet behöver ett nytt system för att registrera betyg.


## Setup glassfish (IntelliJ IDEA Ultimate)
(2025-11-12)
1. Ladda ner `GlassFish 7.0.25 Jakarta EE Platform` från https://glassfish.org/download och notera var du extraherar det.
2. I IntelliJ, gå till plugins och sök efter `GlassFish`. Installera den.
3. Skapa en ny Run-configuration och välj Glassfish Server -> Local
4. I samma dialog, höger meny längst upp finns en rad `Application Server`: välj `configure` och i dialogen välj det directory du extraherade till i steg 1.
5. Längre ner finns `Glassfish Server settings`: Välj `domain1` som ett test.

Testa köra nu. Om det fungerar, super! 

Om du fick felet `'"%~$PATH;i"' is not recognized as an internal or external command, operable program or batch file.` fortsätt med stegen nedan (endast Windows):
1. Öppna systemvariablerna. I det nedre fönstret, lägg till en variabel med namnet `JAVA_HOME` och sätt värdet det directory ditt JDK finns i (i mitt fall var detta under `C:\Users\Zekas\.jdks`).
    - Alternativt, ladda ner JDK 21 från [Adoptium](https://adoptium.net/temurin/releases?version=21). Välj då platsen där det installerades (förmodligen `C:\Program Files\Eclipse Adoptium`)
2. Hitta systemvariabeln `Path`. I den, lägg till en ny rad med värdet `%JAVA_HOME%\bin`.
