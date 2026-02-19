Acknoledgement of AI usage
AI used: Claude Sonnet 4.6
Usage for tag A-BetterGui

Prompt - A-BetterGui
Change Gui to make it look friendlier.
Result
Adopted a blue background and removed the 'flip' style of texting that normal chats have. Chat resembles a 'blue' Discord-ish vibe. For some reason, it added ★ symbols everywhere which were illegal characters that could not be loaded.
Manual update
Removed all the stars. Change User to yellow and BuddiBoi green. Increase all font sizes. Removed maxWidth 500px from DialogBox so that text does not automatically wrap at 500px.

Prompt - A-BetterGui
Add a dynamic typing response for BuddiBoi to make it feel more interactive
Result
Updated MainWindow.java and MainWindow.fxml to include a small delay along with a dynamic "Typing" plus moving dots at the back to simulate typing. i.e. "Typing." -> "Typing.." -> "Typing..."
Manual update
Increase delay by 1 second

Prompt - A-Personality
Could you help me change the text that BuddiBoi returns so that it sounds more like its name?
Result
Made BuddiBoi much more affirming using responses like 'Got it!' or "Understood'. Added supportive responses as well such as 'Keep it up!' or 'Wonderful!'.
Manual update
No updates

Prompt - C-NaturalDates
My current program allows me write dates as day names. i.e. "Mon" or "Tue". But I would like if those dates also accept military time behind them as an optional argument. i.e. "Mon 1500" or "Tue 2359".
Result
Added a parseMilitaryTime(String) method and added a section that splits the input String into day of the week and the military time to be parsed
Manual update
No updates

Prompt - General
Wrote several prompts to generate the JavaDocs for certain classes or methods
Results
Various JavaDocs following the Java Coding Standard
Manual update
No updates

Prompt - Storage.java
Encountered problem when trying to save and load data when testing via the .jar file
Result
Added resolveFilePath() method in Storage.java
Manual update
Added JavaDocs
