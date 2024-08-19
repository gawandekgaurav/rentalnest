import React from 'react';
import './AboutUs.css'; // Import CSS for AboutUs

const AboutUs = () => {
  return (
    <div className="about-us-container">
      <h1>About Us</h1>
      <p>Welcome to RentalNest, your go-to platform for finding and managing rental properties.</p>
      <p>At RentalNest, our mission is to connect landlords and tenants seamlessly through an easy-to-use and efficient platform. We understand the complexities of the rental market and aim to simplify the process for both landlords and tenants.</p>
      <section>
        <h2>Our Team</h2>
        <div className="team-member">
          <img src="/team-member1.jpg" alt="Team Member 1" className="team-image" />
          <div className="team-info">
            <h3>Jane Doe</h3>
            <p>Co-Founder & CEO</p>
            <p>Jane is passionate about real estate and technology, leading our vision and strategy to create a user-friendly platform.</p>
          </div>
        </div>
        <div className="team-member">
          <img src="/team-member2.jpg" alt="Team Member 2" className="team-image" />
          <div className="team-info">
            <h3>John Smith</h3>
            <p>Co-Founder & CTO</p>
            <p>John brings his expertise in software development to build and maintain our platform, ensuring it meets the highest standards.</p>
          </div>
        </div>
      </section>
      <section>
        <h2>Our Values</h2>
        <ul className="values-list">
          <li><strong>Integrity:</strong> We uphold the highest standards of honesty and fairness.</li>
          <li><strong>Innovation:</strong> We are committed to continuous improvement and leveraging technology to enhance our services.</li>
          <li><strong>Customer Focus:</strong> Our users are at the heart of everything we do. We strive to exceed their expectations.</li>
        </ul>
      </section>
      <section>
        <h2>Contact Us</h2>
        <p>If you have any questions or need assistance, please feel free to <a href="mailto:support@rentalnest.com">email us</a>.</p>
      </section>
    </div>
  );
};

export default AboutUs;
