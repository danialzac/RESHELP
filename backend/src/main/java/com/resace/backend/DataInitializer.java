package com.resace.backend;

import com.resace.backend.model.AnswerOption;
import com.resace.backend.model.Paper;
import com.resace.backend.model.Question;
import com.resace.backend.model.Topic;
import com.resace.backend.repository.QuestionRepository;
import com.resace.backend.repository.TopicRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Seeds placeholder RES exam content on first startup.
 * Replace with real licensed content before public launch.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;

    @Override
    public void run(String... args) {
        if (topicRepository.count() > 0) {
            log.info("Seed data already present — skipping initialization.");
            return;
        }

        log.info("Seeding RES Exam Bank placeholder content...");
        seedPaper1();
        seedPaper2();
        log.info("Seed complete.");
    }

    private void seedPaper1() {
        Topic legislation = saveTopic("Real Estate Legislation", "p1-legislation", Paper.PAPER_1,
            "Key acts and regulations governing Singapore real estate transactions.");
        Topic ownership = saveTopic("Property Ownership & Tenure", "p1-ownership", Paper.PAPER_1,
            "Types of property ownership, tenure, and title in Singapore.");
        Topic market = saveTopic("Property Market Overview", "p1-market", Paper.PAPER_1,
            "Singapore property market structure, segments, and key data.");
        Topic housing = saveTopic("Housing Policies & HDB", "p1-housing", Paper.PAPER_1,
            "Government housing policies, HDB schemes, and eligibility rules.");
        Topic tax = saveTopic("Property Taxation", "p1-tax", Paper.PAPER_1,
            "Stamp duties, property tax, GST implications for real estate.");

        // Legislation questions
        saveQuestion(legislation, Paper.PAPER_1,
            "Under the Estate Agents Act, which body is responsible for licensing real estate agencies in Singapore?",
            "MEDIUM",
            "The Council for Estate Agencies (CEA) was established under the Estate Agents Act 2010 to regulate the real estate agency industry in Singapore. It licenses estate agents and salespersons.",
            new String[][]{
                {"A", "Housing Development Board (HDB)", "false"},
                {"B", "Council for Estate Agencies (CEA)", "true"},
                {"C", "Urban Redevelopment Authority (URA)", "false"},
                {"D", "Monetary Authority of Singapore (MAS)", "false"}
            });

        saveQuestion(legislation, Paper.PAPER_1,
            "Which of the following is NOT a requirement under the Estate Agents Act for a licensed salesperson?",
            "MEDIUM",
            "The Estate Agents Act requires salespersons to pass the RES examination, be registered with CEA, and be employed by a licensed estate agent. There is no requirement to hold a university degree.",
            new String[][]{
                {"A", "Pass the RES examination", "false"},
                {"B", "Be registered with CEA", "false"},
                {"C", "Hold a university degree", "true"},
                {"D", "Be employed by a licensed estate agent", "false"}
            });

        saveQuestion(legislation, Paper.PAPER_1,
            "The Conveyancing and Law of Property Act (Cap. 61) primarily governs which of the following?",
            "HARD",
            "The Conveyancing and Law of Property Act governs conveyancing (the transfer of property) and related property law matters such as the creation, transfer, and enforcement of property interests.",
            new String[][]{
                {"A", "The licensing of property agents", "false"},
                {"B", "The transfer and ownership of real property", "true"},
                {"C", "The valuation of properties", "false"},
                {"D", "The taxation of property transactions", "false"}
            });

        saveQuestion(legislation, Paper.PAPER_1,
            "Under the Land Titles Act, what is the effect of registration of a transfer of land?",
            "HARD",
            "Under the Torrens system (Land Titles Act), registration of a transfer confers indefeasible title on the registered proprietor, meaning the title cannot be defeated except in limited circumstances like fraud.",
            new String[][]{
                {"A", "The transfer becomes effective from the date of signing", "false"},
                {"B", "The transferee gains indefeasible title upon registration", "true"},
                {"C", "The transfer is only effective after stamp duty payment", "false"},
                {"D", "The previous owner retains a right of first refusal", "false"}
            });

        saveQuestion(legislation, Paper.PAPER_1,
            "A salesperson who acts for both the buyer and seller in the same transaction without consent is committing what kind of breach?",
            "EASY",
            "Acting for both buyer and seller in the same transaction without proper disclosure and consent creates a conflict of interest. This is prohibited under CEA's Code of Ethics and Professional Client Care.",
            new String[][]{
                {"A", "A breach of stamp duty requirements", "false"},
                {"B", "A conflict of interest breach", "true"},
                {"C", "A CPD requirement breach", "false"},
                {"D", "A tenancy agreement breach", "false"}
            });

        // Ownership questions
        saveQuestion(ownership, Paper.PAPER_1,
            "Which type of property tenure in Singapore means the owner holds the property forever with no time limit?",
            "EASY",
            "Freehold tenure means the owner holds the property indefinitely with no expiry. In Singapore, freehold properties are typically described as 999-year leasehold or estate in fee simple.",
            new String[][]{
                {"A", "99-year leasehold", "false"},
                {"B", "Freehold", "true"},
                {"C", "30-year leasehold", "false"},
                {"D", "Tenancy at will", "false"}
            });

        saveQuestion(ownership, Paper.PAPER_1,
            "In a joint tenancy, what happens to a deceased owner's share of the property?",
            "MEDIUM",
            "In a joint tenancy, the right of survivorship applies. Upon the death of one joint tenant, their share automatically passes to the surviving joint tenants — it does not form part of the deceased's estate.",
            new String[][]{
                {"A", "It passes according to the deceased's will", "false"},
                {"B", "It passes automatically to the surviving joint tenant(s)", "true"},
                {"C", "It reverts to the government", "false"},
                {"D", "It is divided equally among all heirs", "false"}
            });

        saveQuestion(ownership, Paper.PAPER_1,
            "What distinguishes tenancy-in-common from joint tenancy?",
            "MEDIUM",
            "In tenancy-in-common, each owner holds a defined (often unequal) share and can dispose of their share independently, including through a will. There is no right of survivorship.",
            new String[][]{
                {"A", "Tenancy-in-common owners share the property equally", "false"},
                {"B", "Each tenant-in-common owns a distinct share and can transfer it independently", "true"},
                {"C", "Tenancy-in-common is only available for commercial properties", "false"},
                {"D", "Tenancy-in-common requires government approval to transfer shares", "false"}
            });

        saveQuestion(ownership, Paper.PAPER_1,
            "A 99-year leasehold flat with 30 years remaining on the lease — what is the main concern for a buyer?",
            "MEDIUM",
            "Properties with shorter remaining lease terms face financing constraints (banks may reduce loan quantum), lower resale value, and CPF usage restrictions. A 30-year remaining lease would significantly affect CPF usage eligibility.",
            new String[][]{
                {"A", "The buyer cannot get a mortgage for any leasehold property", "false"},
                {"B", "Financing and CPF usage may be restricted due to the short remaining lease", "true"},
                {"C", "The government automatically renews the lease at no cost", "false"},
                {"D", "Leasehold properties cannot be sold in the last 40 years of the lease", "false"}
            });

        saveQuestion(ownership, Paper.PAPER_1,
            "Strata title is most commonly associated with which type of property?",
            "EASY",
            "Strata title applies to properties within a larger development where the building is subdivided into separate units. Condominiums and apartments are the most common strata-titled properties in Singapore.",
            new String[][]{
                {"A", "Landed houses on individual land plots", "false"},
                {"B", "Condominiums and apartments", "true"},
                {"C", "Shophouses with no residential component", "false"},
                {"D", "Government-owned industrial facilities", "false"}
            });

        // Market questions
        saveQuestion(market, Paper.PAPER_1,
            "Which government agency is primarily responsible for releasing the Singapore Property Price Index (PPI)?",
            "MEDIUM",
            "The Urban Redevelopment Authority (URA) releases the private residential property price index (PPI) each quarter. It is a key indicator of private property market performance in Singapore.",
            new String[][]{
                {"A", "Housing Development Board (HDB)", "false"},
                {"B", "Urban Redevelopment Authority (URA)", "true"},
                {"C", "Council for Estate Agencies (CEA)", "false"},
                {"D", "Building and Construction Authority (BCA)", "false"}
            });

        saveQuestion(market, Paper.PAPER_1,
            "The OCR, RCR, and CCR classifications are used to segment which type of Singapore property market?",
            "EASY",
            "OCR (Outside Central Region), RCR (Rest of Central Region), and CCR (Core Central Region) are geographic classifications used for the private residential property market in Singapore.",
            new String[][]{
                {"A", "Industrial and commercial property", "false"},
                {"B", "Private residential property", "true"},
                {"C", "HDB public housing", "false"},
                {"D", "Strata commercial property only", "false"}
            });

        saveQuestion(market, Paper.PAPER_1,
            "What does the Total Debt Servicing Ratio (TDSR) framework govern in Singapore?",
            "MEDIUM",
            "The TDSR framework, introduced by MAS, limits the proportion of a borrower's gross monthly income that can be used to service all debt obligations, including the property loan being applied for. It is set at 55%.",
            new String[][]{
                {"A", "The maximum number of properties a buyer can own", "false"},
                {"B", "The amount of debt a borrower can service relative to income", "true"},
                {"C", "The minimum holding period before a property can be resold", "false"},
                {"D", "The stamp duty rates applicable on property purchases", "false"}
            });

        saveQuestion(market, Paper.PAPER_1,
            "Which of the following best describes the Seller's Stamp Duty (SSD) in Singapore?",
            "MEDIUM",
            "Seller's Stamp Duty (SSD) is a tax imposed on sellers who dispose of residential property within a certain holding period. It was introduced to discourage short-term speculative flipping of properties.",
            new String[][]{
                {"A", "A tax paid by buyers on all property purchases", "false"},
                {"B", "A tax paid by sellers who sell within a specified holding period", "true"},
                {"C", "A tax applied only to commercial properties", "false"},
                {"D", "A recurring annual tax on property ownership", "false"}
            });

        saveQuestion(market, Paper.PAPER_1,
            "What is the primary purpose of the Additional Buyer's Stamp Duty (ABSD)?",
            "EASY",
            "ABSD was introduced as a cooling measure to moderate investment demand and keep Singapore property prices sustainable. It applies additional stamp duty on top of BSD, with rates varying based on buyer profile and number of properties owned.",
            new String[][]{
                {"A", "To fund public housing construction", "false"},
                {"B", "To cool the property market and manage investment demand", "true"},
                {"C", "To replace the existing Buyer's Stamp Duty framework", "false"},
                {"D", "To penalise foreign developers", "false"}
            });

        // Housing policy questions
        saveQuestion(housing, Paper.PAPER_1,
            "Which scheme allows eligible first-time HDB flat buyers to receive a cash grant for purchasing a resale flat?",
            "EASY",
            "The CPF Housing Grant (CHG) provides eligible first-time buyers with a cash grant when purchasing resale HDB flats. The grant amount varies based on income and flat type.",
            new String[][]{
                {"A", "Home Improvement Programme (HIP)", "false"},
                {"B", "CPF Housing Grant (CHG)", "true"},
                {"C", "Selective En Bloc Redevelopment Scheme (SERS)", "false"},
                {"D", "Proximity Housing Grant (PHG)", "false"}
            });

        saveQuestion(housing, Paper.PAPER_1,
            "What is the Minimum Occupation Period (MOP) for a standard HDB Build-To-Order (BTO) flat?",
            "EASY",
            "The MOP for most HDB BTO flats is 5 years from the date the flat is collected (date of key collection or Temporary Occupation Permit). During this period, owners cannot sell or rent out the entire flat.",
            new String[][]{
                {"A", "2 years", "false"},
                {"B", "3 years", "false"},
                {"C", "5 years", "true"},
                {"D", "10 years", "false"}
            });

        saveQuestion(housing, Paper.PAPER_1,
            "Under the Ethnic Integration Policy (EIP), what is the primary objective?",
            "MEDIUM",
            "The Ethnic Integration Policy (EIP) ensures that HDB estates maintain a balanced ethnic mix, preventing the formation of ethnic enclaves and promoting social cohesion in public housing.",
            new String[][]{
                {"A", "To ensure only Singaporean citizens can purchase HDB flats", "false"},
                {"B", "To maintain a balanced ethnic mix in HDB estates", "true"},
                {"C", "To prioritise first-time buyers over investors", "false"},
                {"D", "To limit the number of foreign nationals in each block", "false"}
            });

        saveQuestion(housing, Paper.PAPER_1,
            "A Singapore Permanent Resident (PR) who wishes to buy a new HDB flat directly from HDB — is this possible?",
            "MEDIUM",
            "Only Singapore Citizens are eligible to purchase new HDB flats directly from HDB. Singapore PRs can only purchase resale HDB flats from the open market, subject to eligibility conditions.",
            new String[][]{
                {"A", "Yes, PRs can buy BTO flats directly from HDB", "false"},
                {"B", "No, only Singapore Citizens can buy new HDB flats from HDB", "true"},
                {"C", "Yes, but only for 3-room flats and smaller", "false"},
                {"D", "Yes, after 5 years of PR status", "false"}
            });

        saveQuestion(housing, Paper.PAPER_1,
            "What does the DBSS (Design, Build and Sell Scheme) represent in Singapore's housing history?",
            "HARD",
            "DBSS was a public housing scheme where private developers designed, built, and sold HDB flats. The scheme was discontinued in 2011 due to pricing concerns. DBSS flats are subject to HDB eligibility rules.",
            new String[][]{
                {"A", "A scheme for private developers to build and sell landed housing", "false"},
                {"B", "A discontinued public housing scheme where private developers built HDB-classified flats", "true"},
                {"C", "A current scheme for mixed-use commercial and residential development", "false"},
                {"D", "A scheme for en bloc redevelopment of older HDB estates", "false"}
            });

        // Tax questions
        saveQuestion(tax, Paper.PAPER_1,
            "Buyer's Stamp Duty (BSD) is calculated based on which value?",
            "EASY",
            "BSD is calculated on the higher of the purchase price or the market value of the property. This prevents understatement of purchase price to reduce stamp duty.",
            new String[][]{
                {"A", "The loan amount approved by the bank", "false"},
                {"B", "The higher of the purchase price or market value", "true"},
                {"C", "The assessed annual value as determined by IRAS", "false"},
                {"D", "The lower of the purchase price or market value", "false"}
            });

        saveQuestion(tax, Paper.PAPER_1,
            "Annual Value (AV) of a property is used as the basis for assessing which tax?",
            "EASY",
            "Annual Value (AV) is the estimated gross annual rental of a property if it were rented out. It is determined by IRAS and is the basis for computing property tax.",
            new String[][]{
                {"A", "Buyer's Stamp Duty", "false"},
                {"B", "Property Tax", "true"},
                {"C", "Additional Buyer's Stamp Duty", "false"},
                {"D", "Goods and Services Tax on sale", "false"}
            });

        saveQuestion(tax, Paper.PAPER_1,
            "For residential properties in Singapore, what are the two different property tax rate structures?",
            "MEDIUM",
            "Residential properties in Singapore are subject to owner-occupier rates (lower, progressive) when the owner lives in the property, and non-owner-occupier rates (higher) for investment or rented-out properties.",
            new String[][]{
                {"A", "Corporate rate and individual rate", "false"},
                {"B", "Owner-occupier rate and non-owner-occupier rate", "true"},
                {"C", "Freehold rate and leasehold rate", "false"},
                {"D", "Citizen rate and foreigner rate", "false"}
            });

        saveQuestion(tax, Paper.PAPER_1,
            "Which of the following transactions would typically attract GST in Singapore?",
            "HARD",
            "Commercial property sales and rentals by GST-registered entities attract GST. Residential property sales and rentals are GST-exempt. An HDB shop unit sold by a GST-registered developer would be subject to GST.",
            new String[][]{
                {"A", "A citizen selling their owner-occupied condominium unit", "false"},
                {"B", "Sale of a commercial shop unit by a GST-registered developer", "true"},
                {"C", "A landlord renting out a private residential apartment", "false"},
                {"D", "Transfer of an HDB flat between immediate family members", "false"}
            });

        saveQuestion(tax, Paper.PAPER_1,
            "Seller's Stamp Duty (SSD) for residential properties sold within the first year of purchase is charged at what rate?",
            "MEDIUM",
            "Under the current SSD framework (revised in 2023), the SSD rate for residential properties sold within 1 year of purchase is 12%. The rate decreases for each subsequent year, becoming nil after 3 years.",
            new String[][]{
                {"A", "4%", "false"},
                {"B", "8%", "false"},
                {"C", "12%", "true"},
                {"D", "16%", "false"}
            });
    }

    private void seedPaper2() {
        Topic agencyLaw = saveTopic("Property Agency Law", "p2-agency-law", Paper.PAPER_2,
            "Agency relationships, duties, and legal obligations for estate agents.");
        Topic marketing = saveTopic("Marketing & Property Listings", "p2-marketing", Paper.PAPER_2,
            "Property marketing regulations, listing agreements, and advertising rules.");
        Topic negotiation = saveTopic("Negotiation & Sales Process", "p2-negotiation", Paper.PAPER_2,
            "Sales process, negotiation techniques, and transaction documentation.");
        Topic ethics = saveTopic("Ethics & Professional Conduct", "p2-ethics", Paper.PAPER_2,
            "CEA Code of Ethics, professional responsibilities, and client duties.");
        Topic clientMgmt = saveTopic("Client Relationship Management", "p2-client", Paper.PAPER_2,
            "Client engagement, needs assessment, and long-term relationship building.");

        // Agency law questions
        saveQuestion(agencyLaw, Paper.PAPER_2,
            "In the context of real estate agency, who is the 'principal' in an agency relationship?",
            "EASY",
            "In an agency relationship, the principal is the party who appoints the agent to act on their behalf. In real estate, this is typically the seller (vendor) or landlord who engages the estate agent.",
            new String[][]{
                {"A", "The estate agent acting in the transaction", "false"},
                {"B", "The client who appoints the agent to act on their behalf", "true"},
                {"C", "The buyer who makes an offer on the property", "false"},
                {"D", "The bank financing the transaction", "false"}
            });

        saveQuestion(agencyLaw, Paper.PAPER_2,
            "What is a 'co-broke' arrangement in Singapore real estate?",
            "MEDIUM",
            "A co-broke arrangement is when two agents from different agencies cooperate in a transaction — one representing the seller and one representing the buyer. Commission is typically split between both agents.",
            new String[][]{
                {"A", "When two agents from the same agency share a listing", "false"},
                {"B", "When agents from different agencies cooperate and share commission on a deal", "true"},
                {"C", "When an agent brokers a commercial and residential deal simultaneously", "false"},
                {"D", "When an agent acts for both parties without commission", "false"}
            });

        saveQuestion(agencyLaw, Paper.PAPER_2,
            "An agent owes a fiduciary duty to their client. Which of the following best describes this duty?",
            "MEDIUM",
            "A fiduciary duty requires the agent to act in the best interests of the client, with loyalty and good faith. This includes duties of disclosure, confidentiality, and avoiding conflicts of interest.",
            new String[][]{
                {"A", "To maximise the transaction price regardless of client preference", "false"},
                {"B", "To act with utmost loyalty and in the best interest of the client", "true"},
                {"C", "To ensure the transaction completes within 30 days", "false"},
                {"D", "To maintain a professional indemnity insurance policy", "false"}
            });

        saveQuestion(agencyLaw, Paper.PAPER_2,
            "Under CEA regulations, an estate agent must provide a client with which document before marketing a property?",
            "MEDIUM",
            "Before marketing a property, an estate agent must have a signed Estate Agency Agreement (EAA) with the client. This formalises the agency relationship and sets out the terms including commission.",
            new String[][]{
                {"A", "A valuation report from a licensed valuer", "false"},
                {"B", "A signed Estate Agency Agreement (EAA)", "true"},
                {"C", "A copy of the property's title deed", "false"},
                {"D", "A mortgage eligibility letter from a bank", "false"}
            });

        saveQuestion(agencyLaw, Paper.PAPER_2,
            "What is the main purpose of the CEA's prescribed Estate Agency Agreement (EAA)?",
            "EASY",
            "The prescribed EAA standardises the terms of engagement between the client and the estate agent, ensuring transparency in commission rates, scope of service, duration, and the agent's obligations.",
            new String[][]{
                {"A", "To allow agents to charge higher commission rates", "false"},
                {"B", "To standardise and formalise the terms of engagement between agent and client", "true"},
                {"C", "To replace the Option to Purchase document", "false"},
                {"D", "To register the property sale with the Singapore Land Authority", "false"}
            });

        // Marketing questions
        saveQuestion(marketing, Paper.PAPER_2,
            "Under CEA's advertising guidelines, which of the following is mandatory in a property advertisement?",
            "MEDIUM",
            "CEA requires that property advertisements include the salesperson's name and CEA registration number, the agency name and licence number. This allows consumers to verify the identity and credentials of the advertising agent.",
            new String[][]{
                {"A", "The owner's full name and NRIC number", "false"},
                {"B", "The salesperson's name and CEA registration number", "true"},
                {"C", "The property's last transacted price", "false"},
                {"D", "The estimated rental yield of the property", "false"}
            });

        saveQuestion(marketing, Paper.PAPER_2,
            "An agent advertises a property as having a '5-minute walk to MRT' when the actual distance is a 15-minute walk. This is most likely a violation of which principle?",
            "EASY",
            "Making false or misleading statements in property advertisements is a breach of the duty of honesty and the prohibition against misrepresentation. It may also violate the Consumer Protection (Fair Trading) Act.",
            new String[][]{
                {"A", "The HDB resale levy rules", "false"},
                {"B", "The prohibition against false and misleading representations", "true"},
                {"C", "The CPD training requirements", "false"},
                {"D", "The Seller's Stamp Duty regulations", "false"}
            });

        saveQuestion(marketing, Paper.PAPER_2,
            "What is the primary difference between an exclusive listing and an open listing?",
            "MEDIUM",
            "In an exclusive listing, only the appointed agent can market the property, and the seller typically pays commission even if they find a buyer themselves. An open listing allows multiple agents to market the same property.",
            new String[][]{
                {"A", "Exclusive listings are only for luxury properties above $3 million", "false"},
                {"B", "Only one agent markets the property exclusively under an exclusive listing", "true"},
                {"C", "Open listings require the seller to pay commission to all agents involved", "false"},
                {"D", "Exclusive listings do not require a signed agency agreement", "false"}
            });

        saveQuestion(marketing, Paper.PAPER_2,
            "When advertising a property with a stated price, what obligation does the agent have if the property has already received an offer?",
            "HARD",
            "When a property has received an offer, the agent should update marketing materials promptly and disclose the status accurately to avoid misleading potential buyers. Advertising a property as available when it is under offer without disclosure may constitute misrepresentation.",
            new String[][]{
                {"A", "Continue advertising at the same price until the option is exercised", "false"},
                {"B", "Update the advertisement to reflect the current status accurately", "true"},
                {"C", "Immediately remove all advertising regardless of whether the offer is accepted", "false"},
                {"D", "Advertise the property at a higher price to generate competing offers", "false"}
            });

        saveQuestion(marketing, Paper.PAPER_2,
            "Under the Personal Data Protection Act (PDPA), what must an agent obtain before collecting a client's personal data?",
            "MEDIUM",
            "The PDPA requires organisations including estate agents to obtain consent from individuals before collecting, using, or disclosing their personal data. The purpose of collection must also be disclosed.",
            new String[][]{
                {"A", "A statutory declaration from the client", "false"},
                {"B", "Consent from the individual before collecting their personal data", "true"},
                {"C", "Approval from CEA for each data collection activity", "false"},
                {"D", "A notarised authorisation letter", "false"}
            });

        // Negotiation questions
        saveQuestion(negotiation, Paper.PAPER_2,
            "In a private property sale, what document is typically issued by the seller to formalise the offer after agreement is reached?",
            "EASY",
            "An Option to Purchase (OTP) is issued by the seller to the buyer, granting the buyer an option to purchase the property within a specified period (typically 14 days for private property). The buyer pays an option fee to obtain the OTP.",
            new String[][]{
                {"A", "A Letter of Intent (LOI)", "false"},
                {"B", "An Option to Purchase (OTP)", "true"},
                {"C", "A Sale and Purchase Agreement (SPA)", "false"},
                {"D", "A Tenancy Agreement (TA)", "false"}
            });

        saveQuestion(negotiation, Paper.PAPER_2,
            "The option fee for a private residential property transaction is typically what percentage of the purchase price?",
            "MEDIUM",
            "The option fee for private property transactions is typically between 1% to 5% of the purchase price, paid by the buyer when receiving the Option to Purchase. When the option is exercised, a further payment (often making up to 5-10% total) is paid.",
            new String[][]{
                {"A", "0.1% of the purchase price", "false"},
                {"B", "1% to 5% of the purchase price", "true"},
                {"C", "10% of the purchase price", "false"},
                {"D", "20% of the purchase price", "false"}
            });

        saveQuestion(negotiation, Paper.PAPER_2,
            "What happens if a buyer decides NOT to exercise the Option to Purchase within the option period?",
            "MEDIUM",
            "If the buyer does not exercise the OTP within the option period, the option lapses and the buyer forfeits the option fee paid. The seller is then free to sell to another party.",
            new String[][]{
                {"A", "The buyer gets a full refund of the option fee", "false"},
                {"B", "The option fee is forfeited and the seller can sell to others", "true"},
                {"C", "The transaction proceeds automatically at the end of the option period", "false"},
                {"D", "The buyer can apply to court to extend the option period", "false"}
            });

        saveQuestion(negotiation, Paper.PAPER_2,
            "What is the purpose of a Letter of Intent (LOI) in a commercial property transaction?",
            "MEDIUM",
            "An LOI (or Letter of Offer) in commercial property expresses the buyer's or tenant's intention to proceed with the transaction on specified terms. It is typically non-binding but signals commitment before formal documentation.",
            new String[][]{
                {"A", "It is a legally binding contract for the sale of property", "false"},
                {"B", "It expresses intent to transact and outlines preliminary terms before formal contracts", "true"},
                {"C", "It replaces the Option to Purchase for all commercial deals", "false"},
                {"D", "It must be signed by a lawyer to be valid", "false"}
            });

        saveQuestion(negotiation, Paper.PAPER_2,
            "In a property negotiation, what does 'best and final offer' typically signal?",
            "EASY",
            "A 'best and final offer' request signals that the seller wants buyers to submit their highest and most competitive offer, typically in a competitive sale situation. It indicates no further negotiation rounds will follow.",
            new String[][]{
                {"A", "The seller is willing to accept offers below the asking price", "false"},
                {"B", "The seller is requesting buyers to submit their most competitive offer with no further rounds", "true"},
                {"C", "Only one buyer is allowed to bid for the property", "false"},
                {"D", "The property is being sold at a government-mandated fixed price", "false"}
            });

        // Ethics questions
        saveQuestion(ethics, Paper.PAPER_2,
            "A client asks their agent to deliberately omit material defects when advertising their property. What should the agent do?",
            "EASY",
            "An agent must not knowingly misrepresent a property or omit material information that would affect a buyer's decision. The agent should advise the client to disclose defects and refuse to participate in misrepresentation.",
            new String[][]{
                {"A", "Comply with the client's instruction to maintain the agency relationship", "false"},
                {"B", "Advise the client that material defects must be disclosed and decline to omit them", "true"},
                {"C", "Report the client to the police immediately", "false"},
                {"D", "Add a disclaimer in the advertisement that buyers must do their own checks", "false"}
            });

        saveQuestion(ethics, Paper.PAPER_2,
            "What does the CEA's Code of Ethics require when an agent has a personal interest in a property being marketed?",
            "MEDIUM",
            "When an agent has a personal or financial interest in a property (e.g., if the agent or their family owns it), full disclosure must be made to the client. This ensures transparency and avoids undisclosed conflicts of interest.",
            new String[][]{
                {"A", "The agent must withdraw from the transaction entirely", "false"},
                {"B", "The agent must disclose their personal interest to all parties", "true"},
                {"C", "The agent can proceed without disclosure if the price is fair", "false"},
                {"D", "The agent only needs to disclose if the client specifically asks", "false"}
            });

        saveQuestion(ethics, Paper.PAPER_2,
            "Continuing Professional Development (CPD) is required for registered salespersons. What is the primary purpose?",
            "EASY",
            "CPD ensures that salespersons keep their knowledge current with legal changes, market developments, and professional standards. CEA mandates CPD hours to maintain registration.",
            new String[][]{
                {"A", "To generate revenue for CEA through training fees", "false"},
                {"B", "To ensure salespersons maintain up-to-date knowledge and skills", "true"},
                {"C", "To replace the need for the RES examination", "false"},
                {"D", "To rank salespersons by competency for client assignment", "false"}
            });

        saveQuestion(ethics, Paper.PAPER_2,
            "When should a salesperson refer a client to a lawyer or other professional?",
            "MEDIUM",
            "Salespersons should refer clients to appropriate professionals (lawyers for legal advice, valuers for valuations, mortgage brokers for financing) whenever the matter requires expertise beyond the agent's scope. Acting outside one's competence is a professional ethics issue.",
            new String[][]{
                {"A", "Only when the transaction involves a disputed property", "false"},
                {"B", "Whenever the matter requires professional expertise beyond the agent's role", "true"},
                {"C", "Only for transactions above $2 million in value", "false"},
                {"D", "Never, as the agent is responsible for all aspects of the transaction", "false"}
            });

        saveQuestion(ethics, Paper.PAPER_2,
            "Which action would most likely result in disciplinary action by CEA?",
            "MEDIUM",
            "Receiving secret profits (undisclosed commissions or kickbacks from third parties) without client consent is a serious breach of fiduciary duty and professional ethics, and would result in disciplinary action by CEA.",
            new String[][]{
                {"A", "Submitting a CPD record one week late", "false"},
                {"B", "Receiving undisclosed commissions from a third party without client consent", "true"},
                {"C", "Using digital platforms to market a property listing", "false"},
                {"D", "Providing a client with comparative market data", "false"}
            });

        // Client management questions
        saveQuestion(clientMgmt, Paper.PAPER_2,
            "What is the first step a salesperson should typically take when engaging a new seller client?",
            "EASY",
            "The first step is to conduct a needs assessment to understand the client's goals, timeline, expectations, and circumstances. This informs the agent's marketing strategy and advice.",
            new String[][]{
                {"A", "Immediately list the property on all online portals", "false"},
                {"B", "Conduct a needs assessment to understand the client's goals and circumstances", "true"},
                {"C", "Request the client to sign the OTP immediately", "false"},
                {"D", "Obtain a bank valuation before meeting the client", "false"}
            });

        saveQuestion(clientMgmt, Paper.PAPER_2,
            "A buyer client complains that their agent showed them properties outside their stated budget. This suggests a failure in which aspect?",
            "EASY",
            "Showing properties outside the buyer's stated budget indicates a failure in understanding and respecting the client's needs and constraints. Good client management requires active listening and matching services to client requirements.",
            new String[][]{
                {"A", "Failure to obtain the signed Estate Agency Agreement", "false"},
                {"B", "Failure to properly understand and respect the client's stated needs and budget", "true"},
                {"C", "Failure to disclose personal interest in the properties shown", "false"},
                {"D", "Failure to submit CPD training records on time", "false"}
            });

        saveQuestion(clientMgmt, Paper.PAPER_2,
            "After successfully completing a transaction for a client, what is a good practice for building long-term client relationships?",
            "EASY",
            "Following up after transaction completion — checking in on the client's experience, providing useful market updates, and staying in touch — builds long-term relationships and referral potential, which is the foundation of a sustainable real estate practice.",
            new String[][]{
                {"A", "Immediately request the client to sign another agency agreement", "false"},
                {"B", "Follow up with the client and provide continued value through market updates and check-ins", "true"},
                {"C", "Avoid contacting the client to prevent appearing pushy", "false"},
                {"D", "Transfer the client file to a junior agent to free up capacity", "false"}
            });

        saveQuestion(clientMgmt, Paper.PAPER_2,
            "A landlord asks their agent to discriminate against certain nationalities when screening tenants. What should the agent do?",
            "MEDIUM",
            "Agents must not assist clients in unlawful discrimination. While landlords have some discretion in tenant selection, systematic discrimination based on nationality, race, or religion may violate Singapore's fair tenancy principles and the agent should decline.",
            new String[][]{
                {"A", "Comply with the landlord's instruction as the landlord is the client", "false"},
                {"B", "Decline to assist and advise the landlord on fair and lawful tenant selection practices", "true"},
                {"C", "Report the landlord to police before doing anything else", "false"},
                {"D", "Apply the discrimination criteria quietly without documenting it", "false"}
            });

        saveQuestion(clientMgmt, Paper.PAPER_2,
            "What should an agent do if they discover that a client has provided false information on a mortgage application?",
            "HARD",
            "If an agent discovers that a client has provided false information on a mortgage application, this may constitute fraud. The agent should not assist in or facilitate fraudulent activity. Appropriate steps include advising the client to correct the information and declining to continue if the client refuses.",
            new String[][]{
                {"A", "Ignore it as the mortgage application is not the agent's responsibility", "false"},
                {"B", "Advise the client to correct the information and decline to assist with the fraudulent application", "true"},
                {"C", "Help the client adjust the remaining documents to be consistent with the false information", "false"},
                {"D", "Proceed with the transaction as the bank will conduct its own checks", "false"}
            });
    }

    private Topic saveTopic(String name, String slug, Paper paper, String description) {
        return topicRepository.save(Topic.builder()
            .name(name)
            .slug(slug)
            .paper(paper)
            .description(description)
            .build());
    }

    private void saveQuestion(Topic topic, Paper paper, String questionText, String difficulty,
        String explanation, String[][] options) {
        Question question = Question.builder()
            .topic(topic)
            .paper(paper)
            .questionText(questionText)
            .difficulty(difficulty)
            .explanation(explanation)
            .active(true)
            .build();

        List<AnswerOption> answerOptions = new java.util.ArrayList<>();
        for (String[] opt : options) {
            answerOptions.add(AnswerOption.builder()
                .question(question)
                .optionLabel(opt[0])
                .optionText(opt[1])
                .correct(Boolean.parseBoolean(opt[2]))
                .build());
        }
        question.setAnswerOptions(answerOptions);
        questionRepository.save(question);
    }
}
