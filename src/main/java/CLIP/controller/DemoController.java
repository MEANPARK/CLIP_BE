package CLIP.controller;

import CLIP.response.Response;
import CLIP.service.PythonExecutor.PythonExecutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/Demo")
public class DemoController {
    private final PythonExecutorService pythonExecutorService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/execute")
    public Response executePython(@RequestParam String item) {
        try {
            return Response.success(pythonExecutorService.executePythonScript(item));
        } catch (Exception e) {
            return Response.success("Error: " + e.getMessage());
        }
    }
}
