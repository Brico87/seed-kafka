import { JAVA_JACKSON_PRESET, JavaFileGenerator } from '@asyncapi/modelina';
import path from 'path';

// Where should the models be placed relative to root maven project?
const MODEL_DIR = `target/generated-sources/com/ft/demo/generated/model`;

const FINAL_OUTPUT_PATH = path.resolve(__dirname, '../../', MODEL_DIR);
// Setup the generator and all it's configurations
const generator = new JavaFileGenerator({
  presets: [JAVA_JACKSON_PRESET]
});

// Load the input from file, memory, or remotely.
// Here we just use a local AsyncAPI file
import INPUT from '../../contract_asyncapi_v2.0.yaml';
const input = INPUT;

// Generate all the files
generator.generateToFiles(input, FINAL_OUTPUT_PATH, {
  packageName: 'com.ft.demo.generated.model'
});