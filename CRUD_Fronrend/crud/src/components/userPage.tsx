import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './css/userPage.css'

interface User {
  email: string;
  fName: string;
  lName: string;
  phoneNumber: string;
  password: string;
  id:number;
}

const UsersComponent: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [editUser, setEditUser] = useState<User | null>(null); // To store user being edited

  useEffect(() => {
    axios.get('http://localhost:8080/auth/users') // Replace with actual API endpoint 
      .then(response => {
        if (response.data && response.data.status && response.data.data) {
          setUsers(response.data.data);
          console.log(response.data.data);
        }
      })
      .catch(error => {
        console.error('Error fetching users:', error);
      })
  }, []);

  const handleEditClick = (user: User) => {
    setEditUser(user); // Set the user to be edited
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (editUser) {
      setEditUser({
        ...editUser,
        [e.target.name]: e.target.value,
      });
    //   console.log(editUser);
    }
  };

  const handleFormSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (editUser) {
      // Send the updated data to the backend
    //   console.log(editUser);
      axios.put(`http://localhost:8080/auth/update/${editUser.id}`, editUser) // Replace with actual API endpoint
        .then(response => {
          setUsers(users.map(user => user.email === editUser.email ? editUser : user));
          if(response.status)
          {
            window.alert(" Details Updatation Success!!");
          }
          else
          {
            window.alert("Details Updatation Failed!!");
          }
          setEditUser(null); // Close the edit form
        })
        .catch(error => {
          console.error('Error updating user:', error);
          window.alert("Details Updatation Failed!!");
        });
    }
  };

 
  return (
    <div>
      <h2>Users List</h2>
      <table className="user-table">
        <thead>
          <tr>
            <th>Email</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone Number</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user, index) => (
            <tr key={index}>
              <td>{user.email}</td>
              <td>{user.fName}</td>
              <td>{user.lName || '-'}</td>
              <td>{user.phoneNumber}</td>
              <td>
                <button onClick={() => handleEditClick(user)}>Edit</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Edit form - shows only if a user is being edited */}
      {editUser && (
        <div className="edit-form">
          <h3>Edit User</h3>
          <form onSubmit={handleFormSubmit}>
            <label>
              Email:
              <input type="text" name="email" value={editUser.email} onChange={handleInputChange} disabled />
            </label>
            <label>
              First Name:
              <input type="text" name="fName" value={editUser.fName} onChange={handleInputChange} />
            </label>
            <label>
              Last Name:
              <input type="text" name="lName" value={editUser.lName} onChange={handleInputChange} />
            </label>
            <label>
              Phone Number:
              <input type="text" name="phoneNumber" value={editUser.phoneNumber} onChange={handleInputChange} />
            </label>
            <label>
              Password:
              <input type="text" name="password" value={editUser.password} onChange={handleInputChange} />
            </label>
            <button type="submit">Submit</button>
            <button type="button" onClick={() => setEditUser(null)}>Cancel</button>
          </form>
        </div>
      )}
    </div>
  );
};

export default UsersComponent;
