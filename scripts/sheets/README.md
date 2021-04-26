# Aggregera data från Google sheets
Genererar data från ett träningsark på formatet

```
{
  "startDate": "2016-07-25",
  "trainingData": [
    {
      "name": "Lotta",
      "training": [
        [
          "",
          "60 PT plus cykling 40",
          "trail cykling 60",
          "trail cykling 50",
          "",
          "bakislöpning 30, cykling 30 ",
          "vattenland och promenad 30"
        ],
        [
          "",
          "PT 60 min fokus ben, cykla 30 min",
          "cykling 30 min",
          "",
          "cykling 1h10min trail",
          "Löpning 50 min",
          "kort styrka \"typ mini-tabata\""
        ],
        [
          "Löpning \"hansa style\" längs ringmuren i Visby, 50 situps",
          "",
          "",
          "",
          "Sommargenomgång PT"
        ],
        [
          "",
          "promenad 30 min plus vattenland ",
          "Vattengympa på lyxhotell Mallis ",
          "Grottvandring"
        ],
        [
          "Trail 4 km",
          "",
          "",
          "powerwalk 45 min+styrka",
          "davids tabata, styrka plus löpning",
          "MTB",
          "MTB"
        ],
        [
          "löpning 4 km",
          "cykling 1h",
          "",
          "löpning inkl löpstyrka landet ",
          "Löpning",
          "traillöpning stockby"
        ],
        [
          "Fjällvandring",
          "fjällvandring + MTB",
          "fjällvandring + off trail tävling",
          "fjällvandring ",
          "Kajak",
          "fjällvandring",
          "fjällvandring"
        ]
      ]
    },
    {
      "name": "Peter",
      "training": [
        [
          "15 mc, 8 tabata",
          "45 prom, 8 tabata",
          "Löp t jobbet ",
          "40 m löp",
          "",
          "30 mc",
          "50 löp inkl löpskoln. 8 tabata"
        ],
        ...
```

Varje lista under `training` motsvarar en veckas all 7 träningstillfällen. Om man inte fyllt i ngt för en viss dag BORDE det stå `""` för den dagen, men osäkert.
TODO!

Hämtat från det här exemplet:
https://developers.google.com/sheets/api/quickstart/nodejs

Köra:

```
node .
```

## token.json
Det måste finnas en fil token.js i den här katalogen som innehåller access_token och refresh_token.
Om den inte finns kommer den att genereras (via authentisering av ditt konto hos google) när man kör scriptet.

Om filen finns men är för gammal får man `Error: invalid grant` eller liknande.
I så fall slänger man token.js och kör scriptet igen.

## credentials.json
Man måste skapa `credentials.json`, följ länken kring "Create credentials" på sidan ovan.