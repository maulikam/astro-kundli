import { userService } from '../../services/userService';
import { error as logError, info as logInfo } from '../../lib/logService';
import { ERROR_MESSAGES } from '../../lib/constants/errorMessages';

export default async function handler(req, res) {
  const { method } = req;

  try {
    switch (method) {
      // Handle GET request to fetch all users
      case 'GET': {
        logInfo('Fetching all users...');
        const users = await userService.getAllUsers();
        res.status(200).json(users);
        logInfo('Successfully fetched all users.');
        break;
      }

      // Handle POST request to register a new user
      case 'POST': {
        const userData = req.body;
        try {
          logInfo('Registering a new user...');
          const newUser = await userService.registerUser(userData);
          res.status(201).json(newUser);
          logInfo('User registered successfully.');
        } catch (error) {
          logError('Error registering user:', error);
          res.status(400).json({ message: error.message || ERROR_MESSAGES.REGISTRATION_FAILED });
        }
        break;
      }

      // Handle unsupported methods
      default: {
        res.setHeader('Allow', ['GET', 'POST']);
        res.status(405).end(`Method ${method} Not Allowed`);
        break;
      }
    }
  } catch (error) {
    logError('Error handling request:', error);
    res.status(500).json({ message: ERROR_MESSAGES.GENERAL_ERROR });
  }
}
