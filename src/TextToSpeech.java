import javax.speech.*;    
import java.util.*;    
import javax.speech.synthesis.*;    

public class TextToSpeech    
{    
String speaktext; 
public void dospeak(String speak)    
{    
    if (speak == null || speak.trim().isEmpty()) return;

    String voiceName = "kevin16";

    try {
        System.setProperty("FreeTTSSynthEngineCentral", "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

        SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);

        Synthesizer synth = Central.createSynthesizer(desc);
        synth.allocate();
        desc = (SynthesizerModeDesc) synth.getEngineModeDesc();
        Voice[] voices = desc.getVoices();
        Voice voice = null;
        for (Voice entry : voices) {
            if(entry.getName().equals(voiceName)) {
                voice = entry;
                break;
            }
        }
        synth.getSynthesizerProperties().setVoice(voice);
        synth.resume();
        synth.speakPlainText(speak, null);
        synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
        //synth.deallocate();

    } catch(Exception ex) {
        String message = " missing speech.properties in " + System.getProperty("user.home") + "\n";
        System.out.println("" + ex);
        System.out.println(message);
        ex.printStackTrace();
    }
}
}