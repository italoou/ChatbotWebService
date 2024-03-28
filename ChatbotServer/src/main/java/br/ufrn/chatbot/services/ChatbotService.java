package br.ufrn.chatbot.services;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

	private static final boolean TRACE_MODE = false;
	
	public String sendQuestionToBot(String text) {
		System.out.println("Human: " + text);
		
		String resourcesPath = getResourcesPath();
		MagicBooleans.trace_mode = TRACE_MODE;
		Bot bot = new Bot("super", resourcesPath);
		Chat chatSession = new Chat(bot);
		bot.brain.nodeStats();

		String response;
		
		if ((text == null) || (text.length() < 1))
			text = MagicStrings.null_input;
		if (text.equals("q")) {
			return "";
		} else if (text.equals("wq")) {
			bot.writeQuit();
			return "";
		} else {
			String request = text;
			if (MagicBooleans.trace_mode)
				System.out.println(
						"STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0)
								+ ":TOPIC=" + chatSession.predicates.get("topic"));
			response = chatSession.multisentenceRespond(request);
			while (response.contains("&lt;"))
				response = response.replace("&lt;", "<");
			while (response.contains("&gt;"))
				response = response.replace("&gt;", ">");
			
			System.out.println("Bot: " + response);
		}
		
		return response;
	}
	
	private static String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 16);
		String resourcesPath = path + File.separator + "ChatbotServer" + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}
}
