package code.connector

import code.bankconnectors._
import code.bankconnectors.vJune2017.InboundAccountJune2017
import code.bankconnectors.vMar2017.InboundStatusMessage
import code.model._
import code.setup.{DefaultConnectorTestSetup, DefaultUsers, ServerSetup}
import code.util.Helper.MdcLoggable
import net.liftweb.common.{Box, Full}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by zhanghongwei on 14/07/2017.
  */
object MockedJune2017Connector extends ServerSetup  
  with Connector with DefaultUsers  
  with DefaultConnectorTestSetup with MdcLoggable {
  
  implicit override val nameOfConnector = "MockedCardConnector"
  
  
  //These bank id and account ids are real data over adapter  
  val bankIdAccountId = BankIdAccountId(BankId("obp-bank-x-gh"),AccountId("KOa4M8UfjUuWPIXwPXYPpy5FoFcTUwpfHgXC1qpSluc"))
  val bankIdAccountId2 = BankIdAccountId(BankId("obp-bank-x-gh"),AccountId("tKWSUBy6sha3Vhxc/vw9OK96a0RprtoxUuObMYR29TI"))
  
  override def getBankAccounts(username: String, forceFresh: Boolean): Box[List[InboundAccountJune2017]] = {
    Full(
      InboundAccountJune2017(
        errorCode = "OBP-6001: ...",
        List(InboundStatusMessage("ESB", "Success", "0", "OK")),
        cbsToken = "cbsToken",
        bankId = bankIdAccountId.bankId.value,
        branchId = "222", 
        accountId = bankIdAccountId.accountId.value,
        accountNumber = "123", 
        accountType = "AC", 
        balanceAmount = "50",
        balanceCurrency = "EUR", 
        owners = Nil,
        viewsToGenerate = "Owner" :: "Public" :: "Accountant" :: "Auditor" :: Nil,
        bankRoutingScheme = "iban", 
        bankRoutingAddress = "bankRoutingAddress",
        branchRoutingScheme = "branchRoutingScheme",
        branchRoutingAddress = " branchRoutingAddress",
        accountRoutingScheme = "accountRoutingScheme",
        accountRoutingAddress = "accountRoutingAddress",
        accountRules = Nil
      ) :: InboundAccountJune2017(
        errorCode = "OBP-6001: ...",
        List(InboundStatusMessage("ESB", "Success", "0", "OK")),
        cbsToken = "cbsToken",
        bankId = bankIdAccountId2.bankId.value,
        branchId = "222",
        accountId = bankIdAccountId2.accountId.value,
        accountNumber = "123",
        accountType = "AC",
        balanceAmount = "50",
        balanceCurrency = "EUR",
        owners = Nil,
        viewsToGenerate = "Owner" :: "Public" :: "Accountant" :: "Auditor" :: Nil,
        bankRoutingScheme = "iban",
        bankRoutingAddress = "bankRoutingAddress",
        branchRoutingScheme = "branchRoutingScheme",
        branchRoutingAddress = " branchRoutingAddress",
        accountRoutingScheme = "accountRoutingScheme",
        accountRoutingAddress = "accountRoutingAddress",
        accountRules = Nil
      ) :: Nil
    )
  }

  override def getBankAccountsFuture(username: String, forceFresh: Boolean): Future[Box[List[InboundAccountJune2017]]] = Future {
    getBankAccounts(username, forceFresh)
  }
}

