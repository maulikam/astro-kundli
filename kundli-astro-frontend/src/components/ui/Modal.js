// src/components/ui/Modal.js

import React, { useEffect } from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';

/**
 * A reusable modal dialog for displaying forms or messages
 * @param {Object} props - The properties passed to the Modal component
 */
const Modal = ({ isOpen, onClose, children, title, className }) => {
  useEffect(() => {
    const handleEsc = (event) => {
      if (event.key === 'Escape') {
        onClose();
      }
    };

    if (isOpen) {
      document.addEventListener('keydown', handleEsc);
    } else {
      document.removeEventListener('keydown', handleEsc);
    }

    return () => document.removeEventListener('keydown', handleEsc);
  }, [isOpen, onClose]);

  if (!isOpen) return null;

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div
        className={classNames('modal-container', className)}
        onClick={(e) => e.stopPropagation()} // Prevent closing when clicking inside the modal
      >
        {title && <div className="modal-header"><h2>{title}</h2></div>}
        <div className="modal-content">{children}</div>
        <div className="modal-footer">
          <button onClick={onClose} className="close-button">
            Close
          </button>
        </div>
      </div>
    </div>
  );
};

Modal.propTypes = {
  isOpen: PropTypes.bool.isRequired,
  onClose: PropTypes.func.isRequired,
  children: PropTypes.node.isRequired,
  title: PropTypes.string,
  className: PropTypes.string,
};

export default Modal;
