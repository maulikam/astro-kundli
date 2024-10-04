import { kundliService } from '../../../services/kundliService';
import { error as logError, info as logInfo } from '../../../lib/logService';
import { ERROR_MESSAGES } from '../../../lib/constants/errorMessages';

export default async function handler(req, res) {
  const {
    query: { id },
    method,
  } = req;

  try {
    switch (method) {
      // Handle GET request to fetch Kundli by ID
      case 'GET': {
        logInfo(`Fetching Kundli with ID: ${id}...`);
        const kundli = await kundliService.getKundliById(id);
        if (!kundli) {
          logError(`Kundli with ID: ${id} not found.`);
          return res.status(404).json({ message: ERROR_MESSAGES.KUNDLI_NOT_FOUND });
        }
        res.status(200).json(kundli);
        logInfo(`Successfully fetched Kundli with ID: ${id}.`);
        break;
      }

      // Handle PUT request to update Kundli by ID
      case 'PUT': {
        const updatedData = req.body;
        logInfo(`Updating Kundli with ID: ${id}...`);
        const updatedKundli = await kundliService.updateKundli(id, updatedData);
        if (!updatedKundli) {
          logError(`Kundli with ID: ${id} not found for updating.`);
          return res.status(404).json({ message: ERROR_MESSAGES.KUNDLI_NOT_FOUND });
        }
        res.status(200).json(updatedKundli);
        logInfo(`Successfully updated Kundli with ID: ${id}.`);
        break;
      }

      // Handle DELETE request to remove Kundli by ID
      case 'DELETE': {
        logInfo(`Deleting Kundli with ID: ${id}...`);
        const deletedKundli = await kundliService.deleteKundli(id);
        if (!deletedKundli) {
          logError(`Kundli with ID: ${id} not found for deletion.`);
          return res.status(404).json({ message: ERROR_MESSAGES.KUNDLI_NOT_FOUND });
        }
        res.status(200).json({ message: 'Kundli deleted successfully' });
        logInfo(`Successfully deleted Kundli with ID: ${id}.`);
        break;
      }

      // Handle unsupported methods
      default: {
        res.setHeader('Allow', ['GET', 'PUT', 'DELETE']);
        res.status(405).end(`Method ${method} Not Allowed`);
        break;
      }
    }
  } catch (error) {
    logError('Error handling request:', error);
    res.status(500).json({ message: ERROR_MESSAGES.GENERAL_ERROR });
  }
}
