package CLIP.service.PythonExecutor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PythonExecutorService {

    public List<String> executePythonScript(String item) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python3", "/BackServer/CLIPServerDemo/script.py", item);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();

            // JSON 문자열을 리스트로 변환
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result.toString(), new TypeReference<List<String>>(){});
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 오류 발생시 null 반환
        }
    }
}