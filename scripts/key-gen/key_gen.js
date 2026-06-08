/**
 * Requires NodeJS 18+
 * --------------------------------------------------
 * Run with
 * $ node key_gen.js
 * --------------------------------------------------
 *
 * This script generates a cryptographically secure random key and prints it
 * as a Base64 encoded string.
 *
 * When prompted, enter the desired key size in bytes (for example, 32).
 * The script uses crypto.randomBytes to fill a byte array of that size and then
 * encodes the result in Base64.
 *
 * Example output:
 * Result: <base64-encoded-string>
 *
 * Use SIGINT (CTRL + C) to cancel.
 */

const readline = require('node:readline/promises');
const crypto = require('node:crypto');
const { stdin, stdout } = require('node:process');

const run = async () => {
  const rl = readline.createInterface({ input: stdin, output: stdout });
  console.log('--------------------------------------------------');
  console.log('KEY GENERATOR');
  console.log('--------------------------------------------------');
  console.log('Type the size of the key you want to generate:');
  console.log('---- SIGINT (CTRL + C) to cancel.');

  const expectedSize = await rl.question('');

  rl.close();

  const size = Number.parseInt(expectedSize, 10);

  console.log(`Generating Base64 encoded string for a ${size} byte array`);
  const result = crypto.randomBytes(size).toString('base64');

  console.log(`Result: ${result}`);
};

run().catch((err) => {
  console.error('Error generating key');
  console.error(err);
});
