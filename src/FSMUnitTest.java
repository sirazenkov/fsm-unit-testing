import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.HashMap;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

public class FSMUnitTest {
	static Class<?> fsm;
	
	static String FSM_NAME_FILE = "FSMname.txt";
	static String FSM_INPUT_FILE = "FSMinput.csv";
	static String FSM_OUTPUT_FILE = "FSMoutput.csv";
	static String FSM_TEST_FILE = "FSMtest.txt";
	
	static HashMap<String,FunctionInvocation> inputs = new HashMap<>();
	static HashMap<String,ReturnValue> outputs = new HashMap<>();
	
    @BeforeAll
    static void setupClass() {
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(FSM_NAME_FILE));
			String className = fileReader.readLine();
			fsm = Class.forName(className);
			
			fileReader = new BufferedReader(new FileReader(FSM_INPUT_FILE));
			String line = ""; // Reading inputs
			while ((line = fileReader.readLine()) != null) {
				FunctionInvocation func = new FunctionInvocation(line);
				inputs.put(line.split(",")[0], func);
			}
			// Reading outputs
			fileReader = new BufferedReader(new FileReader(FSM_OUTPUT_FILE));
			while ((line = fileReader.readLine()) != null) {
				ReturnValue ret = new ReturnValue(line);
				outputs.put(line.split(",")[0], ret);
			}
		} catch (FileNotFoundException e) { System.out.println(e); }
		  catch (IOException e) { System.out.println(e); }
		  catch (ClassNotFoundException e) { System.out.println(e); }
    }

    @TestFactory
	@Execution(ExecutionMode.CONCURRENT)
    ArrayList<DynamicNode> testGenerator() {
		ArrayList<DynamicNode> testSequences = new ArrayList<DynamicNode>();
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(FSM_TEST_FILE));
			int counter = 0;
			String line = "";
			while ((line = fileReader.readLine()) != null) {
				String testinfo = String.format("Applying sequence %d", ++counter);
				testSequences.add(dynamicContainer(testinfo, test(line)));
			}
		} catch (FileNotFoundException e) { System.out.println(e); }
		  catch (IOException e) { System.out.println(e); }
		return testSequences;
	}
	
	ArrayList<DynamicTest> test(String testSequence) {
		ArrayList<DynamicTest> tests = new ArrayList<DynamicTest>();
		try {
			Object uut = fsm.newInstance();
		
			int testLength;
			String[] tmp = testSequence.split(" ");
			testLength = tmp.length;
		
			String[] seqInputs = new String[testLength];
			String[] seqOutputs = new String[testLength];
		
			for(int i = 0; i < testLength; i++) {
				String[] tmp2 = tmp[i].split("/");
				seqInputs[i] = tmp2[0];
				seqOutputs[i] = tmp2[1];
			}
		
			for(int i = 0; i < testLength; i++) {
				Method method = uut.getClass().getMethod((inputs.get(seqInputs[i])).name, inputs.get(seqInputs[i]).getParamTypes()); 
				Object o = method.invoke(uut, inputs.get(seqInputs[i]).arguments);
				Object o_correct = outputs.get(seqOutputs[i]).value;
				String testinfo = String.format("Applying symbol %d", i+1);
				tests.add(dynamicTest(testinfo,() -> assertEquals(o_correct, o)));
			}
		} catch (IllegalArgumentException e) { System.out.println(e); }
		  catch (IllegalAccessException e) { System.out.println(e); }
		  catch (InvocationTargetException e) { System.out.println(e); }
		  catch (SecurityException e) { System.out.println(e); }
		  catch (NoSuchMethodException e) { System.out.println(e); }
		  catch (InstantiationException e) { System.out.println(e); }
		return tests;
	}
}

class FunctionInvocation {
	String name;
	Object[] arguments;

	public FunctionInvocation(String info) {
		name = info.split(",")[1];
		String[] args = (info.split(",")[2]).split(" ");
		int args_length = args.length;
		arguments = new Object[args_length];
		for(int i = 0; i < args_length; i++) {
			String[] tmp = args[i].split(":");
			switch(tmp[0]) {
				case "Boolean": arguments[i] = Boolean.parseBoolean(tmp[1]);
								break;
				case "Integer": arguments[i] = Integer.parseInt(tmp[1]);
								break;				
				default: arguments[i] = tmp[1];	// String
						 break;			  
			}
		}
	}
	
	Class<?>[] getParamTypes() {
		int args_size = arguments.length;
		Class<?>[] result = new Class<?>[args_size];
		for(int i = 0; i < args_size; i++) {
			result[i] = arguments[i].getClass();
		}
		return result;
	}
}

class ReturnValue {
	Object value;
	
	public ReturnValue(String info) {
		String[] tmp = (info.split(",")[1]).split(":");
		switch(tmp[0]) {
				case "Boolean": value = Boolean.parseBoolean(tmp[1]);
								break;
				case "Integer": value = Integer.parseInt(tmp[1]);
								break;				
				default: value = tmp[1];
						 break;			  
			}
	}
}