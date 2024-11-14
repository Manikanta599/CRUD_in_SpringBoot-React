import React, { useState, useEffect } from 'react';
import { Table, Button, Modal, Form, Input, message, Popconfirm,Typography } from 'antd';
import { PlusOutlined, EditOutlined, DeleteOutlined,   } from '@ant-design/icons';
import axios from 'axios';
const { Title } = Typography;
interface EmpData {
    department: string;
    position: string;
    salary: number;
    hireDate: string;
    address: string;
}

interface User {
    key: string;
    empId: number;
    email: string;
    fName: string;
    lName: string;
    phoneNumber: string;
    password: string;
    empData: EmpData;
}

const AdminPage: React.FC = () => {
    const [users, setUsers] = useState<User[]>([]);
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [editingUser, setEditingUser] = useState<User | null>(null);
    const [form] = Form.useForm();

    // Fetch data from API on component load
    useEffect(() => {
        axios.get('http://localhost:8080/empDetails/with-reg')
            .then(response => {
                const fetchedUsers = response.data.data.map((item: any) => ({
                    key: item.empDetails.empId.toString(),
                    empId: item.empDetails.empId,
                    email: item.regDetails.email,
                    fName: item.regDetails.fName,
                    lName: item.regDetails.lName,
                    phoneNumber: item.regDetails.phoneNumber,
                    password: item.regDetails.password,
                    empData: {
                        department: item.empDetails.department,
                        position: item.empDetails.position,
                        salary: item.empDetails.salary,
                        hireDate: item.empDetails.hireDate,
                        address: item.empDetails.address,
                    }
                }));
                setUsers(fetchedUsers);
            })
            .catch(error => {
                message.error('Failed to load users');
            });
    }, []);

    const openModal = (user?: User) => {
        setIsModalVisible(true);
        if (user) {
            form.setFieldsValue(user.empData);
            setEditingUser(user);
        } else {
            form.resetFields();
            setEditingUser(null);
        }
    };

    const handleModalSubmit = () => {
        form.validateFields().then(values => {
            if (editingUser) {
                // Send only empData fields for update
                const empData = {
                    department: values.department,
                    position: values.position,
                    salary: values.salary,
                    hireDate: values.hireDate,
                    address: values.address,
                };
                axios.put(`http://localhost:8080/empDetails/update/${editingUser.empId}`, empData)
                    .then(() => {
                        setUsers(users.map(user =>
                            user.empId === editingUser.empId ? { ...user, empData } : user
                        ));
                        message.success('User updated successfully');
                    })
                    .catch(error => {
                        message.error('Failed to update user');
                    });
            }
            setIsModalVisible(false);
            form.resetFields();
        }).catch(info => {
            console.error('Validate Failed:', info);
        });
    };

    const handleDelete = (empId: number) => {
        axios.delete(`http://localhost:8080/empDetails/${empId}`)
            .then(() => {
                setUsers(users.filter(user => user.empId !== empId));
                message.success('User deleted successfully');
            })
            .catch(error => {
                message.error('Failed to delete user');
            });
    };

    const columns = [
        { title: 'Email', dataIndex: 'email', key: 'email' },
        { title: 'First Name', dataIndex: 'fName', key: 'fName' },
        { title: 'Last Name', dataIndex: 'lName', key: 'lName' },
        { title: 'Phone Number', dataIndex: 'phoneNumber', key: 'phoneNumber' },
        { title: 'Department', dataIndex: ['empData', 'department'], key: 'department' },
        { title: 'Position', dataIndex: ['empData', 'position'], key: 'position' },
        { title: 'Salary', dataIndex: ['empData', 'salary'], key: 'salary' },
        { title: 'Hire Date', dataIndex: ['empData', 'hireDate'], key: 'hireDate' },
        { title: 'Address', dataIndex: ['empData', 'address'], key: 'address' },
        {
            title: 'Actions',
            key: 'actions',
            render: (_: any, record: User) => (
                <>
                    <Button icon={<EditOutlined />} onClick={() => openModal(record)} style={{ marginRight: 8 }} />
                    <Popconfirm title="Are you sure to delete?" onConfirm={() => handleDelete(record.empId)}>
                        <Button icon={<DeleteOutlined />} danger />
                    </Popconfirm>
                </>
            ),
        },
    ];

    return (
        <div style={{ padding: '20px', backgroundColor: '#f5f5f5', minHeight: '100vh' }}>
            {/* <Button type="primary" icon={<PlusOutlined />} onClick={() => openModal()} style={{ marginBottom: 16 }}>
                Add User
            </Button> */}
            <div style={{
                backgroundColor: '#fff',
                padding: '20px',
                borderRadius: '8px',
                boxShadow: '0px 4px 12px rgba(0, 0, 0, 0.1)',
                marginBottom: '20px',
                textAlign: 'center'
            }}>
                <Title level={2} style={{ margin: 0, color: '#1890ff' }}>
                    Welcome, Admin
                </Title>
            </div>
            
            <Table dataSource={users} columns={columns} />

            <Modal
                title={editingUser ? 'Edit User' : 'Add User'}
                visible={isModalVisible}
                onCancel={() => setIsModalVisible(false)}
                onOk={handleModalSubmit}
                okText={editingUser ? 'Update' : 'Add'}
            >
                <Form form={form} layout="vertical">
                    <Form.Item name="department" label="Department">
                        <Input />
                    </Form.Item>
                    <Form.Item name="position" label="Position">
                        <Input />
                    </Form.Item>
                    <Form.Item name="salary" label="Salary">
                        <Input />
                    </Form.Item>
                    <Form.Item name="hireDate" label="Hire Date">
                        <Input />
                    </Form.Item>
                    <Form.Item name="address" label="Address">
                        <Input />
                    </Form.Item>
                </Form>
            </Modal>
        </div>
    );
};

export default AdminPage;
